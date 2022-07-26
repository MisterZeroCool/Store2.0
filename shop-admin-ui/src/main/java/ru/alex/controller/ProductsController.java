package ru.alex.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.alex.controller.repr.ProductRepr;
import ru.alex.error.NotFoundException;
import ru.alex.persist.repo.BrandRepository;
import ru.alex.persist.repo.CategoryRepository;
import ru.alex.service.ProductService;


@Controller
public class ProductsController {

    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    @Autowired
    public ProductsController(ProductService productService, CategoryRepository categoryRepository,
                              BrandRepository brandRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping("/products")
    public String adminProductsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/product/{id}/edit")
    public String adminEditProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product_form";
    }

    @DeleteMapping("/product/{id}/delete")
    public String adminDeleteProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Products");
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/product/create")
    public String adminCreateProduct(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", new ProductRepr());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product_form";
    }

    @PostMapping("/product")
    public String adminUpsertProduct(Model model, RedirectAttributes redirectAttributes, ProductRepr product) {
        model.addAttribute("activePage", "Products");

        try {
            productService.save(product);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating product", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (product.getId() == null) {
                return "redirect:/product/create";
            }
            return "redirect:/product/" + product.getId() + "/edit";
        }
        return "redirect:/products";
    }
}
