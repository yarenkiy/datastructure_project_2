/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2;

/**
 *
 * @author yarenk
 */
public class Node {
      int  height,index;
        String Data;
        Node left, right;

      

        public Node(  int index,String Data) {
            this.Data = Data;
           this.index=index;
            this.left = null;
            this.right = null;
            height = 1;
        }
         
}
