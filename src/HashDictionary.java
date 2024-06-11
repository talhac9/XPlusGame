/*
CS2210 Assignment 2 X+ Game
Talha Chaudhry
tchaud@uwo.ca
251390028
October 17, 2023
 */

import java.util.LinkedList;
public class HashDictionary  implements DictionaryADT{
    private LinkedList<Data> table[]; // LinkedList array that will be used as the HashDictionary
    private static int numRecords; // Number of records inside the HashDictionary
    private int size; // Size of the HashDictionary

    // Constuctor method that creates a HashDictionary with the size given as parameter
    public HashDictionary(int size) {

        this.size = size;
        table = new LinkedList[size];
        numRecords = 0;

        for (int i = 0; i < size; i++) {  // Sets every index in table to an empty LinkedList of type Data
            table[i] = new LinkedList<Data>();
        }
    }

    // This private method will be used to find the index to store the Data object in the table.
    // Parameters config represents the config of Data object and s represents the size of HashDictionary
    // This method implements the polynomial hash code function given in lecture notes
    private int hashFunction(String config, int s) {
        int val = config.charAt(0);
        for (int i = 1; i < config.length() ; i++) {
            val = (val*39+(int)config.charAt(i)) % s;
        }
        return val;
    }

    // This method puts a Data object into the HashDictionary object. Method will throw a DictionaryException
    // if there is another Data object with the same config as the Data object which we want to be added.
    // Method will return 1 if there is a collision or 0 when there is not,
    @Override
    public int put(Data record) throws DictionaryException {

        int pos = hashFunction(record.getConfiguration(), size); // finds index to store record
        LinkedList<Data> p = table[pos];
        for (Data i : p) {  // checks LinkedList if any Data object has the same config as record in this index
            if (i.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException(); // Throws exception if duplicate config is found
            }
        }
        p.add(record);
        numRecords++;

        if (p.size() > 1) return 1; // Collision occurred when adding record, returns 1
        else return 0; // No collision occurred, returns 0

    }

    // This method removes the record with the given config from the dictionary
    // Throws DictionaryException if record with given config is not found
    @Override
    public void remove(String config) throws DictionaryException  {

        int pos = hashFunction(config, size); // get position of config in HashDictionary
        LinkedList<Data> p = table[pos];
        boolean found = false;

        for (Data i : p) { // Iterates through every Data object in table[pos]
            if (i.getConfiguration().equals(config))  {
                p.remove(i); // Removes the Data object with given config
                found = true;
            }
        }
        if (!found) throw new DictionaryException(); // Data object with given config was not found,
                                                    // throw DictionaryException

    }

    // This method returns the score stored in the record of the dictionary with key config, or -1 if config is
    // not in the dictionary.
    @Override
    public int get(String config) {
        int pos = hashFunction(config, size); // get position of config in HashDictionary
        LinkedList<Data> p = table[pos];

        for (Data i : p) { // Iterates through every Data object in table[pos]
            if (i.getConfiguration().equals(config)) return i.getScore(); // If config found, return that object score
        }
        return -1; // returns -1 if config is not found
    }

    @Override
    // This method returns the number of Data objects stored in the dictionary
    public int numRecords() {
        return numRecords;
    }
}
