/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yarenk
 */
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            File myObj = new File("song.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        ArrayList<Song_class> listOfSongs = new ArrayList<>();
        ArrayList<Node> StoredRanges = new ArrayList<>();
        ArrayList<String> Data = new ArrayList<>();
        ArrayList<String> Song_name = new ArrayList<>();
        ArrayList<String> Artist = new ArrayList<>();
        
       
        
        

        avltree tree = new avltree();
        avltree tree2 = new avltree();
        avltree tree3 = new avltree();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("song.txt"));
            int index = 0;
            while (reader.ready()) {

                
                String parts [] = reader.readLine().split(";");
                 Song_class song = new Song_class(parts[0],parts[1],parts[2],parts[3],parts[4]);
            listOfSongs.add(song);
            
                
                Data.add(parts[2]);
                Song_name.add(parts[0]);
                Artist.add(parts[1]);
              
                tree.root = tree.insert(tree.root, index, Data.get(index));
                tree2.root = tree2.insert(tree2.root, index, Song_name.get(index));
                tree3.root = tree3.insert(tree3.root, index, Artist.get(index));
                 
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i =0; i<listOfSongs.size(); i++){
                System.out.println(listOfSongs.get(i));
            }
                
       
        int value = listOfSongs.size();
        System.out.println("1- add \n" + "2- search songs using the name, artist, or ID fields \n"
                + "3- search ranges by giving lower and upper bounds of an interval for keys(only the ID field \n4- delete" + "\n5- exit");

        while (true) {
            int a = Integer.parseInt(sc.nextLine());

            if (a == 1) {
               
                System.out.println("Enter song name:");
                String name = sc.nextLine();
                System.out.println("Enter artist:");
                String artist = sc.nextLine();
                System.out.println("Enter ID:");
                String id = sc.nextLine();
                System.out.println("Enter genre:");
                String genre = sc.nextLine();
                System.out.println("Enter year:");
                String year = sc.nextLine();
                listOfSongs.add(new Song_class(name ,artist ,  id , genre ,year));
                String get_song = listOfSongs.get(listOfSongs.size() - 1).toString();
                String parts[] = get_song.split(";");
                Data.add((parts[2]));
                Song_name.add(parts[0]);
                Artist.add(parts[1]);

                tree.root = tree.insert(tree.root, value, Data.get(Data.size() - 1));
                tree2.root = tree2.insert(tree2.root, value, Song_name.get(Song_name.size() - 1));
                tree3.root = tree3.insert(tree3.root, value, Artist.get(Artist.size() - 1));
                value++;
                System.out.println("Added succesfully!");
            } else if (a == 2) {

                System.out.println("What input will you be giving? 1- id,2-songName, 3- artistName ");

                int choice3 = Integer.parseInt(sc.nextLine());
                String data = "";

                Node tree_value = null;
                if (choice3 == 1) {
                    tree_value = tree.root;

                    System.out.println("Enter ID");
                    data = sc.nextLine();
                  tree_value=  tree.search(tree_value, data);
                   
                } else if (choice3 == 3) {
                    tree_value = tree3.root;

                    System.out.println("Enter artist name");
                    data = sc.nextLine();
                    System.out.println(data);
                   tree_value= tree.search(tree_value, data);
                } else if (choice3 == 2) {
                    tree_value = tree2.root;
                     
                    System.out.println("Enter song name");
                    data = sc.nextLine();
                    System.out.println(data);
                tree_value= tree.search(tree_value, data);
                    
                }
                if(tree_value!=null){
                    System.out.println(""+listOfSongs.get(tree_value.index).toString());
                 }
                else 
                    System.out.println("No item found for your search criteria");
                
                
            } else if (a == 3) {

                System.out.println("enter the ranges ");

                String range1 = sc.nextLine();
                String range2 = sc.nextLine();
                tree.searchRangeRecursive(tree.root, range1, range2, StoredRanges);
                if (StoredRanges.isEmpty()) {
                    System.out.println("There is nothing in a given range! try again by pressing 3.");
                } else {
                    System.out.println("songs in a given range:");
                    for (int i = 0; i < StoredRanges.size(); i++) {
                        int b = StoredRanges.get(i).index;
                        System.out.println(listOfSongs.get(b).toString());
                    }
                }
                StoredRanges.clear();

            } else if (a == 4) {
                
                
                 
               
                System.out.println("Enter the ID that you want to delete:");
                String delete = sc.nextLine();
                
                int isDeleted = tree.getSize(tree.root);
                  Integer []index= {-1};
                  
                tree.root = tree.deleteNode(tree.root, delete,index);
                
                Song_class song = listOfSongs.get(index[0]);
                tree2.root = tree2.deleteNode(tree2.root, song.getSongName(),index);
                
                tree3.root = tree3.deleteNode(tree3.root, song.getArtist(),index);
                
                
                
                
                if (isDeleted == 0) {
                    System.out.println("There is nothing to delete.");

                } else if (isDeleted - 1 == tree.getSize(tree.root)) {

                    System.out.println(delete + " deleted succesfully!");
                }
                else{
                    System.out.println("ID that you want to delete does not exist in the list.");
                }

            } else if (a == 5) {
                
                System.out.println("bye");
                break;
            } else {
                System.out.println("You entered wrong number. Please try again.");
            }
        }

    }


    
}
