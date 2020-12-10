import com.sun.istack.internal.NotNull;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product{
    private int ProductID;
    private String ProductName;
    private int ProductPrice;
    private  int ProductQuantity;

    Product(int ID,String Name, int Price, int Quantity){
        this.setProductID(ID);
        this.setProductName(Name);
        this.setProductPrice(Price);
        this.setProductQuantity(Quantity);
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
    public void getInfo(){
        System.out.printf("%-12d %-12s %-12d %-12d\n",this.ProductID,this.ProductName,this.ProductPrice,this.ProductQuantity);

    }
}
class ListProduct{
    private List<Product> Products;
    ListProduct(){
        this.setProducts(new ArrayList<Product>());
    }

    public void setProducts(List<Product> products) {
        Products = products;
    }

    public List<Product> getProducts() {
        return Products;
    }
    public void AddProduct(Product product){
        this.getProducts().add(product);
    }
    public void AddProduct1(int ID,String Name, int Price, int Quantity){
        Product tem_product = new Product(ID,Name,Price,Quantity);
        this.getProducts().add(tem_product);
    }
    public void DisplayProducts(){
        List<Product> tem_products = this.getProducts();
        System.out.println("\nDANH SÁCH SẢN PHẨM LÀ");
        System.out.printf("%-12s %-12s %-12s %-12s\n","ID","Name","Price","Quantity");
        for(int i=0;i<tem_products.size();i++){
            tem_products.get(i).getInfo();
        }
    }
    public void DeleteProduct(int ID){
        boolean status = false;
        for(int i=0;i<this.getProducts().size();i++){
            if(this.getProducts().get(i).getProductID()==ID){
                this.getProducts().remove(i);
                status=true;
                break;
            }
        }
        if(status){
            System.out.println("\nXóa thành công");
        }
        else{
            System.out.println("\nID không tồn tại");
        }
    }
    public void SearchProduct(String NameProduct){
        List<Product> search_result = new ArrayList<Product>();
        for(int i=0; i<this.getProducts().size();i++){
            if(this.getProducts().get(i).getProductName()==NameProduct){
                search_result.add(this.getProducts().get(i));
            }
        }
        System.out.println("\nKẾT QUẢ TÌM KIẾM LÀ");
        int length = search_result.size();
        if(length==0){
            System.out.println("Không tìm thấy sản phẩm phù hợp");
        }
        else{
            System.out.printf("%-12s %-12s %-12s %-12s\n","ID","Name","Price","Quantity");
            for(int i=0;i<length;i++){
                search_result.get(i).getInfo();
            }
        }

    }
    public void WriteFile()  {
        try {
            FileWriter myWriter = new FileWriter("output_file.txt");
            List<Product> tem_products = this.getProducts();
            myWriter.write("DANH SÁCH SẢN PHẨM LÀ");
            myWriter.write(String.format("%-12s %-12s %-12s %-12s\n","ID","Name","Price","Quantity"));
            for(int i=0;i<tem_products.size();i++){
               Product tem_product = tem_products.get(i);
                myWriter.write(String.format("%-12d %-12s %-12d %-12d\n",tem_product.getProductID(),tem_product.getProductName(),
                        tem_product.getProductPrice(),tem_product.getProductQuantity()));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void ReadFile() throws IOException,NumberFormatException {
        try {
            File x = new File("input.txt");
            Scanner sc = new Scanner(x);
            while(sc.hasNextLine()) {
                String content = sc.nextLine();
                String[] arrOfCon = content.split(",");
                String Id= "";
                String Name ="";
                String Price="";
                String Quantity="";
                for(int i=0;i<arrOfCon.length;i++){
                    switch (i){
                        case 0: Id= arrOfCon[i];
                        case 1: Name =arrOfCon[i];
                        case 2:Price=arrOfCon[i];
                        case 3: Quantity=arrOfCon[i];
                        default:;
                    }
                }
               try{
                Product product =new Product(Integer.parseInt(Id),Name,Integer.parseInt(Price),Integer.parseInt(Quantity));
                this.getProducts().add(product);}
               catch (NumberFormatException e){
                   System.out.println("Error");
               }


            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }


    }
}
public  class Main {

    public static void main(String[] args) throws IOException {
        ListProduct Products = new ListProduct();
        /*Thêm 4 sản phẩm*/
        Products.AddProduct1(1, "Quần áo", 10000, 100);
        Products.AddProduct1(2, "Quần áo", 10000, 100);
        Products.AddProduct1(3, "Quần áo", 10000, 100);
        Products.AddProduct1(4, "Quần áo", 10000, 100);
        /* Hiện thị */
        Products.DisplayProducts();
        /* Xóa theo Id*/
        Products.DeleteProduct(1);
        Products.DisplayProducts();
        /* Tìm kiếm*/
        Products.SearchProduct("Quần áo");
        /*Ghi vào file*/
        Products.WriteFile();
        /*Đọc từ file*/
        Products.ReadFile();
        Products.DisplayProducts();




    }
}
