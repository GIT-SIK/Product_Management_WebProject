package com.dev.productManagement.product;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 상품관리 조회 페이지
     * @return
     */
    @GetMapping("/")
    public String productListForm() {
        return "product/product.tiles";
    }


    /**
    * 상품관리 검색
    * (로직 미완성)
     */
    @RequestMapping(value = ("/search"), method = {RequestMethod.GET})
    @ResponseBody
    public JSONArray productSearch(
            @RequestParam(value = "date1", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date1,
            @RequestParam(value= "date2", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date2,
            @RequestParam(value= "productCategories", required=false) String pc){

        System.out.println(date1 +" <-> "+ date2 +" / "+ pc);
        JSONArray array = new JSONArray();
        List<JSONObject> listObj = new ArrayList<>();


        for(int i=0; i<10; i++) {
            JSONObject obj1 = new JSONObject();
            obj1.put("boardNum" , i);
            obj1.put("spaceName" , "sn" + i );
            obj1.put("Category" , pc);
            obj1.put("price" , "price"  + i);
            obj1.put("regDate" , date1);
            listObj.add(obj1);

        }

        for(int i=0; i<listObj.size()-1; i++){
            array.add(listObj.get(i));
        }

        return array;
        }

    /**
     * 상품 등록
     */
    @RequestMapping(value = ("/insertProduct"), method = {RequestMethod.POST})
    @ResponseBody
    public boolean insertProduct(@RequestParam Map<String, Object> params) {
        try {
            productService.insertProduct(params);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 상품 조회
     */
    @RequestMapping(value = ("/getProductByAll"), method = {RequestMethod.GET ,RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getProductByAll(@RequestBody Map<String, Object> params) {
        try {
            return productService.getProductByAll(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}