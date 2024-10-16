
package dictionary;
import java.io.PrintWriter;
import java.util.*;
import java.nio.file.Paths;

/**
 *
 * @author Lavinia
 */
public class SaveableDictionary {
    private HashMap<String, String> dictionary;
    private String file;
    
    public SaveableDictionary() {
        this.dictionary = new HashMap<>();
        
    }
    
    public SaveableDictionary(String file) {
        this.dictionary = new HashMap<>();
        this.file = file;
    }
    
    public boolean load(){
    
    try(Scanner scanner = new Scanner(Paths.get(file))) {
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split((":"));
            if(parts.length == 2){
            this.dictionary.put(parts[0], parts[1]); 
            } else {
                return false;
            } 
        }
        
        return true;
        
    } catch (Exception e) {
        System.err.println("Error loading file: " + e.getMessage());
        return false;
    }

    }
    
    public void add(String words, String translation){
        this.dictionary.putIfAbsent(words, translation);
    }
    
    public String translate(String word) {
        String toBeTranslated = "";
        if(this.dictionary.containsKey(word)) {
            toBeTranslated = this.dictionary.get(word);
        } else if( this.dictionary.values().contains(word)) {
            for(String w : this.dictionary.keySet()) {
                if(dictionary.get(w).equals(word)) {
                    toBeTranslated = w;
                }
            }
        } else {
            toBeTranslated = null;
        }
        return toBeTranslated;
    }
    
    public void delete(String word){
        String toBeRemoved = "";
        if(this.dictionary.containsKey(word)) {
            this.dictionary.remove(word); 
        } else if (this.dictionary.values().contains(word)) {
            for(String w : this.dictionary.keySet()) {
                if(dictionary.get(w).equals(word)) {
                    toBeRemoved = w;
                }
            }
            this.dictionary.remove(toBeRemoved);
        }
    }
    
    public boolean save(){
    try(PrintWriter writer = new PrintWriter(this.file)) {
        for(String key : this.dictionary.keySet()) {
            writer.println(key + ":" + this.dictionary.get(key));
        }
        
        writer.close();
        return true;
    } catch (Exception e) {
        return false;
    }
    }
}
