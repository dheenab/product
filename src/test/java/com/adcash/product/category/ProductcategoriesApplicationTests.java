package com.adcash.product.category;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.adcash.product.category.controller.ProductCatalogueController;
import com.adcash.product.category.entity.CategoryVo;
import com.adcash.product.category.entity.ProductVo;
import com.adcash.product.category.entity.exception.NotFoundException;
import com.adcash.product.category.service.CategoryService;
import com.adcash.product.category.service.ProductService;
import com.adcash.product.category.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringRunner.class)
@WebMvcTest(value= ProductCatalogueController.class)
class ProductcategoriesApplicationTests {
    private static final String URL = "/services";
    MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
    MediaType MEDIA_TYPE_XML_UTF8 = new MediaType("application", "xml", java.nio.charset.Charset.forName("UTF-8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService mockProductService;
    @MockBean
    private CategoryService mockCategoryService;
    @MockBean
    private SecurityService securityService;
    @MockBean
    private UserDetailsService userdetailsService;
    

    
	@Test
	@WithMockUser(username = "user1", roles={"USER"})
	public void testRetrieveAllCategories() throws Exception {
		CategoryVo categoryVo1 = new CategoryVo("Electronics", null);
		CategoryVo categoryVo2 = new CategoryVo("Games", null);
		Mockito.when(mockCategoryService.getAllCategories()).thenReturn(Arrays.asList(categoryVo1,categoryVo2));	
		mockMvc.perform(MockMvcRequestBuilders.get(URL+"/categories").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name",Matchers.is("Electronics")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name",Matchers.is("Games"))); 


	}
	
	@Test
	@WithMockUser(username = "user1", roles={"USER"})
	public void testRetrieveAllCategories_inXML() throws Exception {
		CategoryVo categoryVo1 = new CategoryVo("Electro"
				+ "nics", null);
		CategoryVo categoryVo2 = new CategoryVo("Games", null);
		Mockito.when(mockCategoryService.getAllCategories()).thenReturn(Arrays.asList(categoryVo1,categoryVo2));	
		mockMvc.perform(MockMvcRequestBuilders.get(URL+"/categories").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_XML_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("ResponseStatus/data[1]/name").string("Electronics"))
        .andExpect(MockMvcResultMatchers.xpath("ResponseStatus/data[2]/name").string("Games"));

	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testRetrieveAllCategoriesadmin_role() throws Exception {
		CategoryVo categoryVo1 = new CategoryVo("Electronics", null);
		CategoryVo categoryVo2 = new CategoryVo("Games", null);
		Mockito.when(mockCategoryService.getAllCategories()).thenReturn(Arrays.asList(categoryVo1,categoryVo2));	
		mockMvc.perform(MockMvcRequestBuilders.get(URL+"/categories").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name",Matchers.is("Electronics")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name",Matchers.is("Games"))); 


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testRetrieveAllCategories_admin_role_inXML() throws Exception {
		CategoryVo categoryVo1 = new CategoryVo("Electro"
				+ "nics", null);
		CategoryVo categoryVo2 = new CategoryVo("Games", null);
		Mockito.when(mockCategoryService.getAllCategories()).thenReturn(Arrays.asList(categoryVo1,categoryVo2));	
		mockMvc.perform(MockMvcRequestBuilders.get(URL+"/categories").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_XML_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("ResponseStatus/data[1]/name").string("Electronics"))
        .andExpect(MockMvcResultMatchers.xpath("ResponseStatus/data[2]/name").string("Games"));

	}
	
	@Test
	@WithMockUser(username = "user1", roles={"USER"})
	public void testRetrieveAllProducts() throws Exception {
		ProductVo productVo1 = new ProductVo("Toy Car", null);
		ProductVo productVo2 = new ProductVo("Mini Toy", null);
		Mockito.when(mockProductService.getProductsByCategory("games")).thenReturn(Arrays.asList(productVo1,productVo2));	
		mockMvc.perform(MockMvcRequestBuilders.get(URL+"/categories/games/products").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name",Matchers.is("Toy Car")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name",Matchers.is("Mini Toy"))); 


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testRetrieveAllProducts_XML() throws Exception {
		ProductVo productVo1 = new ProductVo("Toy Car", null);
		ProductVo productVo2 = new ProductVo("Mini Toy", null);
		Mockito.when(mockProductService.getProductsByCategory("games")).thenReturn(Arrays.asList(productVo1,productVo2));	
		mockMvc.perform(MockMvcRequestBuilders.get(URL+"/categories/games/products").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_XML_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("ResponseStatus/data").nodeCount(2))
        .andExpect(MockMvcResultMatchers.xpath("ResponseStatus/data[1]/name").string("Toy Car"))
        .andExpect(MockMvcResultMatchers.xpath("ResponseStatus/data[2]/name").string("Mini Toy")); 


	}
	
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testcreateCategory() throws Exception {
		CategoryVo categoryVo1 = new CategoryVo("Electronics", null);
		ObjectMapper ow = new ObjectMapper();
        String categoryString = ow.writeValueAsString(categoryVo1);
		Mockito.when(mockCategoryService.createCategory("Electronics")).thenReturn(true);	
		mockMvc.perform(MockMvcRequestBuilders.post(URL+"/categories/create").contentType(MEDIA_TYPE_JSON_UTF8)
		.content(categoryString)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("CategoryElectronicscreated successfully")));


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testUpdateCategory() throws Exception {
		Mockito.when(mockCategoryService.updateCategorybyName("Electronics", "Electrical")).thenReturn(true);	
		mockMvc.perform(MockMvcRequestBuilders.put(URL+"/categories/update/Electronics?newCategoryName=Electrical").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("CategoryElectricalcreated successfully")));


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testDeleteCategory() throws Exception {
		Mockito.doNothing().when(mockCategoryService).deleteCategory("Electronics");	
		mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/categories/delete/Electronics").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("CategoryElectronicsdeleted successfully")));


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testDeleteCategory_throws_Exception() {
		try {
		Mockito.doThrow(new NotFoundException("unable to delete")).when(mockCategoryService).deleteCategory("Electronics");	
		mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/categories/delete/Electronics").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isInternalServerError());
       	}catch (Exception e) {	
			e.printStackTrace();
		}

	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testcreateProduct() throws Exception {
		ProductVo productVo1 = new ProductVo("Toys", null);
		ObjectMapper ow = new ObjectMapper();
        String categoryString = ow.writeValueAsString(productVo1);
		Mockito.when(mockProductService.createProduct("Toys")).thenReturn(productVo1);	
		mockMvc.perform(MockMvcRequestBuilders.post(URL+"/product/create/").contentType(MEDIA_TYPE_JSON_UTF8)
		.content(categoryString)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("ProductToyscreated successfully")));


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testUpdateProduct() throws Exception {
		Mockito.doNothing().when(mockProductService).updateProduct("Toys", "machineGuns");	
		mockMvc.perform(MockMvcRequestBuilders.put(URL+"/product/update/Toys?newProductName=machineGuns").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("ProductmachineGunsupdated successfully")));


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testDeleteProduct() throws Exception {
		Mockito.doNothing().when(mockProductService).deleteProduct("machineGuns");	
		mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/product/delete/machineGuns").contentType(MEDIA_TYPE_JSON_UTF8)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("ProductmachineGunsdeleted successfully")));


	}
	
	@Test
	@WithMockUser(username = "admin", roles={"ADMIN"})
	public void testAddProductToCategory() throws Exception {
		ProductVo productVo1 = new ProductVo("Toys", Arrays.asList("Electronics"));
		ObjectMapper ow = new ObjectMapper();
        String categoryString = ow.writeValueAsString(productVo1);
		Mockito.when(mockProductService.addProductToCategory(Mockito.any(ProductVo.class))).thenReturn(productVo1);	
		mockMvc.perform(MockMvcRequestBuilders.patch(URL+"/product/add/").contentType(MEDIA_TYPE_JSON_UTF8)
		.content(categoryString)
		.accept(MEDIA_TYPE_JSON_UTF8))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("ProductToysadded successfully")));


	}

}
