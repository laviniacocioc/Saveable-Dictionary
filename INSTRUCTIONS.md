=> Saveable Dictionary Implementation
In this project, I extended a dictionary application to support reading from and writing to a file, allowing for translations both from Finnish to another language and vice versa. The main class created for this purpose is SaveableDictionary, implemented in the dictionary package.

=>Basic Functionality
I started by implementing the basic functionality of the dictionary. The constructor takes no parameters and initializes an empty dictionary. I added the following methods:

add(String words, String translation): This method adds a word along with its translation to the dictionary. Each word can only have one translation, so if a word is added again, it does nothing.

translate(String word): This method retrieves the translation for a given word. If the word is not found, it returns null.

=> The dictionary works as expected with the following example:

SaveableDictionary dictionary = new SaveableDictionary();
dictionary.add("apina", "monkey");
dictionary.add("banaani", "banana");
dictionary.add("apina", "apfe");

System.out.println(dictionary.translate("apina")); // Outputs: monkey
System.out.println(dictionary.translate("monkey")); // Outputs: apina
System.out.println(dictionary.translate("ohjelmointi")); // Outputs: null
System.out.println(dictionary.translate("banana")); // Outputs: banaani
The output confirms that the dictionary successfully translates words to and from Finnish.

=> Deleting Words
Next, I added the delete(String word) method to remove a word and its translation from the dictionary. The implementation ensures that deleting either the Finnish word or its translation will remove both entries.

Here's how the dictionary behaves after implementing deletion:

SaveableDictionary dictionary = new SaveableDictionary();
dictionary.add("apina", "monkey");
dictionary.add("banaani", "banana");
dictionary.add("ohjelmointi", "programming");
dictionary.delete("apina");
dictionary.delete("banana");

System.out.println(dictionary.translate("apina")); // Outputs: null
System.out.println(dictionary.translate("monkey")); // Outputs: null
System.out.println(dictionary.translate("banana")); // Outputs: null
System.out.println(dictionary.translate("banaani")); // Outputs: null
System.out.println(dictionary.translate("ohjelmointi")); // Outputs: programming
This shows that the deletion functionality is working correctly.

=> Reading from a File
I then implemented a constructor public SaveableDictionary(String file) and the load() method, which reads the dictionary from a specified file. The method returns false if the file cannot be opened or read, and true if loading is successful.

The file format consists of lines with words and translations separated by a colon (":"). Using Scanner, I read the file and split each line to populate the dictionary:

SaveableDictionary dictionary = new SaveableDictionary("words.txt");
boolean wasSuccessful = dictionary.load();

if (wasSuccessful) {
    System.out.println("Successfully loaded the dictionary from file");
}

=> Sample output confirms successful loading:

csharp
Copy code
Successfully loaded the dictionary from file
monkey
null
below

=> Saving to a File
Finally, I created the save() method to write the dictionary back to the specified file. This method ensures that changes made to the dictionary are preserved for future use. The file format remains consistent, containing only one-way translations.

=> The final implementation of the dictionary includes:

SaveableDictionary dictionary = new SaveableDictionary("words.txt");
dictionary.load();

// Use the dictionary

dictionary.save();

=> This structure ensures that the dictionary can be loaded from a file at the start and saved back at the end, maintaining all modifications made during the program's execution.
