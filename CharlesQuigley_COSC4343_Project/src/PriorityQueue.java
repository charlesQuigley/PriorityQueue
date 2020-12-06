/*
Priority Queue Class
----------------------------
Created By: Charles Quigley
Version: 1
This class uses a 2D Object array in conjunction with methods to create a Minimum-heap based Priority Queue.
The queue holds pairs (element, key) such that the key represents the priority of the element.
This class is designed to be generic, and thus can hold elements of either
the Integer, Character, Double, Float, or String types. These elements are placed in queue[0][i], where i is some index.
The priority keys of each respective element are held in queue[1][i], where i is some index.
 */
import java.text.DecimalFormat;


/*
E is used as a generic type such that the priority queue can be declared to hold values of type Integer,
Double, Float, Char, or String.
*/
public class PriorityQueue<E>
{

    private Object[][] queue; //2D array of Objects. Object type is used so that the class maintains generic properties
                              //and so that the array can hold Integer, Character, Float, Double, or String data types.
    private int size; //Actual size of array
    private int n; //How filled the array is. For example, the array can be size = 20, but might only have 10 elements
                    //in it. In this scenario, n = 10.

    /**
     *   Class Constructor.
     *   Time complexity = O(1).
     *
     *
     * @param size : the size of the array.
     */
    public PriorityQueue(int size)
    {
        //Declare a 2D array that has size # of rows and 2 columns.
        queue = new Object[size][2];

        this.size = size;
        n = 0; //n = 0 because the array currently holds 0 (element, key) pairs.

    }

    /**
     *         This method acts like the common peek() method in that it allows the user
     *         To view the element at index 0 of the queue.
     *
     *         T(n) = O(1).
     *
     *         This method will go inside the if-statement and perform constant operations if
     *         the queue holds 0 (element, key) pairs. This method will go inside the else-statement
     *         and perform constant operations if the queue holds at least 1 (element, key) pairs.
     *         Thus, it runs in constant time.
     *
     *         Parameters: None.
     *         Return: None.
     */
    public void heap_View_min()
    {
        if(n <= 0)
        {
            System.out.println("The Queue is Empty.");
        }
        else {

            Object queueAtIndexZero = queue[0][0];

            //If the elements we are working with are type Double or Float,
            //then we round the values to 2 decimal places (round up).
            if(queue[0][0] instanceof Double || queue[0][0] instanceof Float)
            {
                queueAtIndexZero = decimalFormat.format(queue[0][0]);
            }

            System.out.printf("Index 0:   Element = "+ queueAtIndexZero +"    Key = "+ queue[0][1] +".");

        }

    }

    /**
     *         This method outputs the contents of the priority queue in their entirety.
     *         This means that all (element, key) pairs that are currently in the queue are printed.
     *         If there are no (element, key) pairs in the queue, then this method simply outputs that the queue is empty.
     *
     *         This method contains 2 for-loops. The outer for-loop iterates once for each (element, key) pair within the queue.
     *         The inner for-loop is used for output formatting. The inner for-loop takes the current length of the string
     *         "Index #%d:   Element = "[element]" " and determines how many spaces should be appended so that
     *         all key values are outputted at the same x-coordinate. This helps readability. As such, the inner-for loop
     *         iterates 0-11 times for each (element, key) pair in the queue.
     *
     *         If we ignore the inner for-loop, this algorithm runs in T(n) = O(n) time.
     *         If we do not ignore it, however, then worst case time for this algorithm is T(n) = O(11 * n),
     *         which is asymptotically equivalent to T(n) = O(n).
     *
     *         Parameters: None.
     *         Return: None.
     */
    public void viewAllElements()
    {

        if(n <= 0)
        {
            System.out.println("\nThe Priority Queue Is Empty!\n");

        }
        else
        {
            String text = "";
            int count = 0;

            for(int i = 0; i < n; i++)
            {
                text = "\nIndex #" + count;

                //This is for output formatting purposes in order to increase readability.
                if(text.length() == 9) //if count >= 0 and count <= 9
                {
                    text = text + " :";
                }
                else //else count > 9
                {
                    text = text + ":";
                }

                //If the elements are of either Float or Double type, output the value rounded to 2 decimals.
                if(queue[i][0] instanceof Float || queue[i][0] instanceof Double)
                {
                    String formattedDecimal = decimalFormat.format(queue[i][0]);
                    text = text + "   Element = \"" + formattedDecimal + "\"";
                }
                else
                {
                    text = text + "   Element = \"" + (E)queue[i][0] + "\"";
                }

                //28+12 = 40 ^ maximum size of text at this point if element name can be at most 12 characters long

                int length = text.length();

                //iterates depending on length of the element name. So if the element was a string named
                //"abcdefghiklm" then this for-loop would iterate 0 times. If the element was a string named
                //"abc" then this for-loop would iterate 9 times.
                for(int j = 0; j < 40 - length; j++)
                {
                    text = text + " ";
                }

                //Print out the index #, the element name, and the key.
                text = text + " Key = " + (E)queue[i][1];
                System.out.print(text);

                count++;
            }
        }


    }

    /**
     * This method increases the key of an element and then calls min_heapify() at that element
     * to maintain min heap properties.
     *
     * T(n) = O(lg(n)) due to min-heapify().
     *
     * @param i : the index of the element whose key is to be increased.
     * @param key : the new value that the key should be increased to.
     * @return true or false depending on whether the key value was increased or not.
     */

    public boolean heap_Increase_Key(int i, int key)
    {
        //If the new key value is less than the current key value, return false
        //to indicate an error.
        if(key < (int)queue[i][1])
        {
            return false;
        }
        else if(!keyLimit(key))
        {
            //If the new key value is not between 11-31, return false to
            //indicate an error.
            return false;
        }
        else
        {
            //Set the new key value.
            queue[i][1] = key;

            //Call min-heapify at the element whose key was increased to regain min heap properties.
            min_Heapify(i);
        }

        //return true to indicate the key change was successful.
        return true;

    }

    /**
     * This method finds the parent node of the child node and returns the index of the parent node.
     *
     * T(n) = O(1).
     *
     * @param childIndex : the index of the child node.
     * @return the index of the parent node.
     */
    private int parent(int childIndex)
    {
        int parentIndex;

        if (childIndex % 2 == 0) {
            parentIndex = (int) Math.floor(childIndex / 2) - 1;
        } else {
            parentIndex = (int) Math.floor(childIndex / 2);
        }

        return parentIndex;

    }

    /**
     *
     *  This method takes the index of the element whose key is to be decreased as
     *         well as the proposed new key value.
     *
     *         It then checks whether the new key number is larger than the current key number.
     *         If it is, the method returns false to indicate that an error has occurred
     *
     *         If the new key value is less than the current key value, the new key replaces the current key
     *         and the parent key of element we're working on is checked within a loop in order to see if the parent and
     *         child nodes should be swapped. This is done in order to maintain Minimum Heap properties.
     *         The method then returns true to show that the process was successful.
     *
     *         Time Complexity: T(n) = O(lg(n))
     *
     * @param i : index of the element whose key we wish to decrease.
     * @param key : the new key value.
     * @return : True or False depending on whether the new key is less than the current key for the element at index i.
     */
    public boolean heap_Decrease_Key(int i, int key) {

        //If the new key is greater than the current key,
        //return false to show that an error has occurred.
        if (key > (int) queue[i][1]) {
            return false;
        }
        else if(!keyLimit(key))
        {
            //If the new key is not within the range 11-31, return false.
            return false;

        }
        else {

            //set the key value of the element at index i to the new key value.
            queue[i][1] = key;

            Object tempElement;
            int tempKey;


            //Check to see if the parent node's key is greater than the current node's new key.
            //If it is, swap the nodes and keep checking the parent key.
            //Basically, work up the nearly complete binary tree in order to maintain
            //minimum heap properties.
            while (i > 0 && (int) queue[parent(i)][1] > (int) queue[i][1]) {

                tempElement = queue[parent(i)][0];
                tempKey = (int) queue[parent(i)][1];
                queue[parent(i)][0] = queue[i][0];
                queue[parent(i)][1] = queue[i][1];
                queue[i][0] = tempElement;
                queue[i][1] = tempKey;

                i = parent(i);

            }
        }

        //return true to show that the new key value was less than the current key value and
        //that the process of maintaining heap properties was successful.
        return true;
    }

    /**
     * This method is used within min_Heap_Insert() in order to determine if the newly added
     * (element, key) pair should move up the heap or down the heap.
     * This depends on the key of the newly added child node.
     * If the child's key is less than the parent's key, then the child node moves up
     * the minimum heap. If the child's key is greater than the parent's key, then we potentially
     * move down the minimum heap.
     *
     * Moving up the heap means calling heap_Decrease_Key().
     * Moving down the heap means calling heap_Increase_Key().
     *
     * Time Complexity: T(n) = O(lg(n)). This is due to either calling heap_Increase_Key() or
     * heap_Decrease_Key().
     *
     * @param i : The index of the newly added node.
     * @param element : The element value of the newly added child node. So, the element in  the (element, key) pair.
     * @param key : The key value of the newly added child node. So, the key in the (element, key) pair.
     */
    private void heap_IncreaseOrDecrease(int i, E element, int key)
    {
        if(n == 0) //If the Priority queue is currently empty, just place the new node at index 0.
        {
            queue[n][0] = element;
            queue[n][1] = key;
        }
        else
        {
            //If the parent's key value is larger than the child's key value, call heap_decrease_key().
            if((int)queue[parent(i)][1] > key)
            {
                queue[i][0] = element;
                queue[i][1] = 999;
                heap_Decrease_Key(i, key);
            }
            else if((int)queue[parent(i)][1] < key) //If the parent's key is smaller than the child's key.
            {
                queue[i][0] = element;
                queue[i][1] = -999;
                heap_Increase_Key(i, key);
            }
            else //If the parent's key is the same value as the child's key.
            {
                //Then the child node will not move from its position,
                //which is the last position in the queue.
                queue[i][0] = element;
                queue[i][1] = key;
            }

        }
    }

    /**
     * This method takes the size of the Priority Queue by 1.
     * It does this by creating a new 2D object array that can hold 1 additional (element, key) pair.
     * (element, key) pairs are then copied from the old 2D array to the new one.
     *
     * T(n) = O(n). This is because a temporary 2D array is created to hold each (element, key) pair of the queue.
     * The queue is then instantiated with a new 2D array and the queue is populated by the (element, key) pairs
     * found in the temporary array. So, O(2n) is asymptotically equivalent to O(n).
     */
    private void resizeArray()
    {
        //Create a temporary array that can hold all the (element, key) pairs found in queue.
        Object[][] tempArray = new Object[size][2];

        //Populate the temporary array with all of the (element, key) pairs found in queue.
        for(int i = 0; i < size; i++)
        {
            tempArray[i][0] = queue[i][0];
            tempArray[i][1] = queue[i][1];
        }

        //Instantiate queue with a new 2D array that can hold one extra (element, key) pair.
        queue = new Object[n+1][2];

        //Populate the new queue with all of the (element, key) pairs found in the temporary array.
        for(int i = 0; i < size; i++)
        {
            queue[i][0] = tempArray[i][0];
            queue[i][1] = tempArray[i][1];
        }

    }

    /**
     * This method adds a new (element, key) pair to the Priority Queue. It increases the Queue's size by 1
     * If there is not enough space to hold the additional (element, key) pair. It then determines
     * if the newly added (element, key) pair should move up or down the queue's minimum heap.
     *
     * T(n) = O(n). This is because resizeArray() is called if the queue is not large enough to hold
     * the new (element, key) pair, and resizeArray() has a time complexity of O(n).
     * We also call heap_increaseOrDecrease(), which has time complexity O(lgn), but since O(n) > O(lgn),
     * this algorithm has time O(n).
     *
     * @param element : The new element value that is to be added.
     * @param key : The new key value that is to be added.
     * @return : False if an error has occurred. True if the (element, key) pair was successfully added to the queue.
     */
    public boolean min_Heap_Insert(E element, int key)
    {
        String elementRepresentedAsString = "" + (E) element;

        //If the elements we are working with are of type String, they cannot be more than
        //12 characters long. This is for output formatting purposes in order to increase readability.
        //Return false to show that the (element, key) pair was not added to the queue.
        if(element instanceof String && elementRepresentedAsString.length() > 12)
        {
            System.err.printf("\nERROR: For output formatting purposes, elements cannot be more than 12 characters long. " +
                    "Thus, the element \"%s\" was not added to the queue.", elementRepresentedAsString);
            return false;
        }
        else if(key < 11 || key > 31) //The key must be between 11-31.
        {
            return false;
        }
        else
        {

            //If the queue is not large enough to hold the new (element, key) pair,
            //Increase the size of the queue by 1.
            if(n >= size)
            {
                resizeArray();
                size = n + 1;
            }


            //Determine if the newly added element should move up the minimum heap,
            //or stay in its current position (which is the last position of the heap).
            heap_IncreaseOrDecrease(n, element, key);
            n = n + 1;

        }

        //Return true to showcase that the (element, key) pair was added to the queue.
        return true;

    }

    /**
     * This method deletes an (element, key) pair from the queue.
     * The last (element, key) pair in the heap is then moved to the deleted node's position.
     * Min_Heapify() is then called at this position to move the node down the heap in order to maintain
     * Minimum Heap properties.
     *
     * T(n) = O(lg(n)) due to Min_Heapify().
     *
     * @param i : The index of the node / (element, key) pair that is to be deleted.
     * @return True if the node has been deleted. False if the index specified does not exist in the queue.
     */
    public boolean min_Heap_Delete(int i)
    {
        //If the index specified by the user is not within the range of indices
        //found in the queue, then return false to indicate an error.
        if(i < 0 || i > n -1)
        {
            return false;
        }

        //Place the last node in the deleted node's position.
        queue[i][0] = queue[n-1][0];
        queue[i][1] = queue[n-1][1];

        //Decrease the number of elements in the queue.
        n = n - 1;

        //Min_heapify() at the index the last node was moved to.
        min_Heapify(i);

        //Return true to indicate that the selected node has been deleted.
        return true;
    }

    /**
     * This method extracts an (element, key) pair with the highest priority from the queue.
     * Highest priority means lowest key value, and since we implement the Priority Queue as
     * a minimum heap, we extract the (element, key) pair at index 0.
     * The last (element, key) pair in the heap is then moved to index 0 (the root of the tree).
     * Min_Heapify() is then called at this position to move the node down the heap in order to maintain
     * Minimum Heap properties.
     *
     * T(n) = O(lg(n)) due to min_Heap_Delete().
     *
     * @return : The (element, key) pair with the highest priority in an array of size 2.
     *           array[0] holds the element and array[1] holds the key.
     */
    public Object[] extract_Max_Priority_Element()
    {
        //Will hold the (element, key) pair that is extracted from the queue.
        Object[] maxPriorityElem = new Object[2];

        //If the element is of type Float or Double,
        //return the element rounded to 2 decimal places.
        if(queue[0][0] instanceof Float || queue[0][0] instanceof Double)
        {
            //decimalFormat.format() rounds the number to 2 decimal places.
            String formattedDecimal = decimalFormat.format(queue[0][0]);
            maxPriorityElem[0] = Float.parseFloat(formattedDecimal);
            maxPriorityElem[1] = queue[0][1];
        }
        else
        {
            maxPriorityElem[0] = queue[0][0]; //Holds the element
            maxPriorityElem[1] = queue[0][1]; //Holds the key
        }


        //Call min_heap_delete on the node at index 0.
        min_Heap_Delete(0);


        return maxPriorityElem;
    }

    /**
     * This method takes the index of a node as its parameter.
     * It then checks the left and right children nodes to determine if one of their keys
     * is smaller then the key of the node specified. If a child key's node is smaller,
     * the child node with the smallest key value is swapped with the key of the node specified.
     *
     * T(n) = O(lg(n)).
     *
     * @param i : The index of the node whose child nodes should be checked.
     */
    private void min_Heapify(int i)
    {
        int L = i * 2 + 1; //Index of left child
        int R = i * 2 + 2; //Index of right child

        int smallest;

        Object tempElement;
        int tempKey;

        //If the left child's key is less than the key of the node at index i.
        if(L <= n && (int)queue[L][1] < (int)queue[i][1])
        {
            smallest = L;
        }
        else
        {
            smallest = i;
        }

        //If the right child's key is less than either the key of the
        // node at index i or the key of the left child node.
        if(R <= n && (int)queue[R][1] < (int)queue[smallest][1])
        {
            smallest = R;
        }

        if(smallest != i)
        {
            //Swap the node with the smallest key
            //and the node at index i.
            tempElement = queue[i][0];
            tempKey = (int)queue[i][1];

            queue[i][0] = queue[smallest][0];
            queue[i][1] = queue[smallest][1];

            queue[smallest][0] = tempElement;
            queue[smallest][1] = tempKey;

            //Recursive call
            min_Heapify(smallest);
        }
    }

    /**
     * This method checks whether a key value is within the range of 11-31.
     *
     * T(n) = O(1)
     *
     * @param key : The key value to be checked.
     * @return : True if the key is within the range of 11-31. False if it is not.
     */
    private boolean keyLimit(int key)
    {
        if(key < 11 || key > 31)
        {
            return false;
        }

        return true;

    }

    /**
     * This method checks the element at index 0 of the queue in order to determine
     * The type of the queue's elements. This can be useful in determining what element type the queue can hold.
     *
     * T(n) = O(1).
     *
     * @return : A string value representing what type of elements the queue can hold.
     */
    public String returnType()
    {
        if(n <= 0)
        {
            System.out.println("\n     The Type Cannot Be Determined Right Now Because The Queue Is Empty.\n");
        }
        else if(queue[0][0] instanceof Integer)
        {
            return "Integer";
        }
        else if(queue[0][0] instanceof Double)
        {
            return "Double";
        }
        else if(queue[0][0] instanceof Float)
        {
            return "Float";
        }
        else if(queue[0][0] instanceof  Character)
        {
            return "Character";
        }
        else if(queue[0][0] instanceof String)
        {
            return "String";
        }

        return "Type Cannot Be Determined.";
    }

    /**
     * This method returns the number of (element, key) pairs currently in the Priority Queue.
     *
     * T(n) = O(1)
     *
     * @return : the number of nodes currently in the Priority Queue.
     */
    public int getSize()
    {
        return n-1;
    }


    /**
     * This is an object of type DecimalFormat that is used
     * when the elements in the queue are Floats or Doubles.
     * It is used to round these numbers to 2 decimal places.
     */
    protected static DecimalFormat decimalFormat = new DecimalFormat("0.00");

}
