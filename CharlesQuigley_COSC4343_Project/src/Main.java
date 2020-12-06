import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //The Experiment involving Priority Queue is conducted within Main().
    public static void main(String[] args)
    {
       Scanner scanObj = new Scanner(System.in);

       PriorityQueue queue = new PriorityQueue<Integer>(20);

       char userChoiceQueueType; //Will hold '1', '2', '3', '4', '5', or '6' depending
                                //on what option for the type of elements in the queue the user picked.

       char userChoiceQueueAction; //Will hold '1', '2', '3', '4', '5', or '6' depending
                                    //on what queue method the user decides to call.

       boolean chooseQueueLoop = true; //The main loop of the program. Setting this to false means ending
                                        //the program.
       boolean queueActionsLoop; //This loop controls the actions of the 3rd menu, in which the user
                                //can decide to insert an element into the queue, delete an element from the queue
                                //change the key value of an element, etc.

       while(chooseQueueLoop) {
           queueActionsLoop = true;
           userChoiceQueueType = optionPromptOne(scanObj); //Display first menu, record the user's choice.

           if (userChoiceQueueType == '6')//If the user chose Option #6. Quit Program
           {
               System.out.println("\n\n~Thank You! Goodbye!~\n\n");
               chooseQueueLoop = false;
               continue;
           }

           //Display the second menu, record the user's choice.
           boolean showStepByStepCreation = optionPromptTwo(scanObj, userChoiceQueueType);

           System.out.println("\n");

           if (userChoiceQueueType == '1') //If the user wants a String type queue
           {
               //Create a new String type queue with initial size 20.
               queue = new PriorityQueue<String>(20);

               //Add twenty (element, key) pairs to the queue.
               createTwentyQueueStrings(queue, showStepByStepCreation);

           } else if (userChoiceQueueType == '2') //If the user wants a Character type queue
           {
               //Create a new Character type queue with initial size 20.
               queue = new PriorityQueue<Character>(20);

               //Add twenty (element, key) pairs to the queue.
               createTwentyQueueCharacters(queue, showStepByStepCreation);

           } else if (userChoiceQueueType == '3') //If the user wants an Integer type queue
           {
               //Create a new Integer type queue with initial size 20.
               queue = new PriorityQueue<Integer>(20);

               //Add twenty (element, key) pairs to the queue.
               createTwentyQueueNumbers(queue, showStepByStepCreation, 1);

           } else if (userChoiceQueueType == '4')//If the user wants a Double type queue
           {
               //Create a new Double type queue with initial size 20.
               queue = new PriorityQueue<Double>(20);

               //Add twenty (element, key) pairs to the queue.
               createTwentyQueueNumbers(queue, showStepByStepCreation, 1.0);

           } else if (userChoiceQueueType == '5') //Float type queue
           {
               //Create a new Float type queue with initial size 20.
               queue = new PriorityQueue<Float>(20);

               //Add twenty (element, key) pairs to the queue.
               createTwentyQueueNumbers(queue, showStepByStepCreation, 1f);
           }


           //This loop displays the 3rd menu, in which the user can decide to
           //insert an element into the queue, delete an element from the queue,
           //Change a key, etc.
           while (queueActionsLoop && chooseQueueLoop) {

               userChoiceQueueAction = optionPromptThree(scanObj, userChoiceQueueType);

               if (userChoiceQueueAction == '1')
               {
                   //The User chose to insert an (element, key) pair into the queue.
                   if (userChoiceQueueType == '1')
                   {
                       //If the user created a String type priority queue.
                       insertAnElement(scanObj, queue, "String");
                   } else if (userChoiceQueueType == '2') {
                       //If the user created a Character type priority queue.
                       insertAnElement(scanObj, queue, 'a');
                   } else if (userChoiceQueueType == '3') {
                       //If the user created an Integer type priority queue.
                       insertAnElement(scanObj, queue, 1);
                   } else if (userChoiceQueueType == '4') {
                       //If the user created a Double type priority queue.
                       insertAnElement(scanObj, queue, 1.0);
                   } else //Should be Float type
                   {
                       insertAnElement(scanObj, queue, 1f);
                   }
               } else if (userChoiceQueueAction == '2')
               {
                   //The User decided to Delete an Element From The Queue
                   deleteAnElement(scanObj, queue);
               } else if (userChoiceQueueAction == '3')
               {
                   //The user decided to Extract an Element with the highest priority from queue.
                   extractMaxPriorityElement(queue);
               } else if (userChoiceQueueAction == '4') {
                   //The user decided to Change the key value of one of the (element, key) pairs
                   //in the queue.
                   changeElementPriority(scanObj, queue);
               } else if (userChoiceQueueAction == '5') {
                   //The user chose to display the contents of the queue.
                   System.out.println("\n\n");
                   System.out.println("ALL ELEMENTS OF THE QUEUE:");
                   System.out.print("--------------------------------------------------------");
                   queue.viewAllElements();
               } else if (userChoiceQueueAction == '6') {
                   //The user decided to exit the 3rd menu and go back to the first menu.
                   queueActionsLoop = false;
               }
           }
       }

    }

    /**
     * This method represents the first menu of the program. Within this menu, the user is asked to choose
     * what type of elements the Priority Queue should hold. The Priority Queue holds (element, key) pairs.
     * While the keys must be integers, the elements can be either String, Character, Integer, Double, or Float types.
     * Since the user must enter her choice, this method implements user input validation checks.
     *
     * T(n) = O(n), where n is the number of times the user enters invalid input.
     *
     * @param scanObj : The scanner object used as the standard input stream.
     * @return : '1', '2', '3', '4', '5', or '6'.
     */
    public static char optionPromptOne(Scanner scanObj)
    {
        boolean isValidInput = false;
        String userOption = "1";

        while(!isValidInput)
        {
            System.out.println("\n\n");
            System.out.println("Please choose what type of Priority Queue you would like: ");
            System.out.println("---------------------------------------------------------");
            System.out.println("1. String\n2. Character\n3. Int\n4. Double\n5. Float\n6. Quit Program");

            System.out.print("\nYou Choose Option: ");

            userOption = scanObj.nextLine();

            //If the user-input is more than 1 character long
            if(userOption.length() != 1)
            {

                System.err.printf("Sorry, \"%s\" is not an option. Please Enter \"1\", \"2\", \"3\", \"4\", \"5\", or \"6\".", userOption);

            }
            else if((int)userOption.charAt(0) < 49 || (int)userOption.charAt(0) > 54)
            {
                //If the user input is not within Ascii 49-54, which represents '1'-'6'
                System.err.printf("Sorry, \"%s\" is not an option. Please Enter \"1\", \"2\", \"3\", \"4\", \"5\", or \"6\".", userOption);
            }
            else
            {
                //Get out of loop.
                isValidInput = true;
            }

        }


        return userOption.charAt(0);
    }

    /**
     * This method represents the second menu of the program. Within this menu, the user is asked if
     * she wants to view the contents of the queue (the min-heap) after each of the 20 elements is added to it.
     * If the user enters '2', then the contents of the queue will only be displayed once after all 20
     * elements have been added to it.
     * Since the user must enter her choice, this method implements user input validation checks.
     *
     * T(n) = O(n), where n is the number of times the user enters invalid input.
     *
     * @param scanObj : The scanner object used as the standard input stream.
     * @return True if the user wants to view the contents of the queue after each (element, key) pair is added to it.
     * False if the user only wants to view the contents of the queue after all 20 elements have been added.
     */
    public static boolean optionPromptTwo(Scanner scanObj, char userChoice)
    {
        String queueType = "";

        //Used to make the prompt more specific.
        if(userChoice == '1')
        {
            queueType = "String";
        }
        else if(userChoice == '2')
        {
            queueType = "Character";
        }
        else if(userChoice == '3')
        {
            queueType = "Integer";
        }
        else if(userChoice == '4')
        {
            queueType = "Double";
        }
        else
        {
            queueType = "Float";
        }

        boolean isValidOption = false;
        String option = "";

        while(!isValidOption)
        {
            System.out.printf("\nThe %s Priority Queue Will Now Be Instantiated With 20 Elements.", queueType);
            System.out.printf("\nWould You Like To See The Step-By-Step Creation Of The %s Priority Queue?", queueType);
            System.out.println("\n-----------------------------------------------------------------------------");
            System.out.println("1. Yes, Show Me The Step-By-Step Creation.\n2. No, Just Show Me The Queue After All 20 Elements Have Been Added.");
            System.out.print("\nYou Choose Option: ");
            option = scanObj.nextLine();

            if(option.equals("1")|| option.equals("2"))
            {
                isValidOption = true;

            }
            else
            {
                System.err.printf("\nSorry, \"%s\" Is Not A Valid Option. Please Enter Either \"1\" Or \"2\".\n\n", option);

            }
        }

        if(option.equals("1"))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * This method represents the third menu of the program. Within this menu, the user is asked if
     * she wants to Insert a node into the queue, delete a node from the queue, extract the node
     * with the highest priority, change the key value of a node, or view the contents of the queue.
     * Since the user must enter her choice, this method implements user input validation checks.
     *
     * T(n) = O(n), where n is the number of times the user enters invalid input.
     *
     * @param scanObj : The scanner object used as the standard input stream.
     * @param priorityQueueType : The type of the elements the priority queue can hold.
     * @return '1', '2', '3', '4', '5', or '6' depending on which option was chosen.
     *
     */
    public static char optionPromptThree(Scanner scanObj, char priorityQueueType)
    {
        String userInput = "";
        boolean isValidInput = false;
        String queueType;

        //Used to make the prompt more specific.
        if(priorityQueueType == '1')
        {
            queueType = "String";
        }
        else if(priorityQueueType == '2')
        {
            queueType = "Character";
        }
        else if(priorityQueueType == '3')
        {
            queueType = "Integer";
        }
        else if(priorityQueueType == '4')
        {
            queueType = "Double";
        }
        else
        {
            queueType = "Float";
        }


        System.out.println("\n");

        while(!isValidInput)
        {
            System.out.printf("\nWhat Else Would You Like To Do With Your %s Priority Queue?", queueType);
            System.out.println("\n------------------------------------------------------------");
            System.out.println("1. Insert An Element Into The Priority Queue.");
            System.out.println("2. Delete An Element From The Priority Queue.");
            System.out.println("3. Extract An Element With The Highest Priority From The Priority Queue.");
            System.out.println("4. Change The Priority Of An Item That Is Already In The Priority Queue.");
            System.out.println("5. Print All The Elements In The Priority Queue.");
            System.out.println("6. Create A New Priority Queue Of A Different Type.");

            System.out.print("\nYou Choose Option: ");

            userInput = scanObj.nextLine();

            if (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4")
                    && !userInput.equals("5") && !userInput.equals("6")) {
                System.err.printf("Sorry, \"%s\" " +
                        "is not an option. Please Enter \"1\", \"2\", \"3\", \"4\", \"5\", or \"6\".\n\n", userInput);
            }
            else
            {
                isValidInput = true;
            }

        }

        if(userInput.equals("1"))
        {
            return '1';
        }
        else if(userInput.equals("2"))
        {
            return '2';
        }
        else if(userInput.equals("3"))
        {
            return '3';
        }
        else if(userInput.equals("4"))
        {
            return '4';
        }
        else if(userInput.equals("5"))
        {
            return '5';
        }
        else
        {
            return '6';
        }


    }


    /**
     * This method adds 20 (element, key) pairs to the queue. The elements are Strings.
     * If showStepByStepCreation = true, then this method will display the contents of the queue
     * after each (element, key) pair is added to it.
     * If showStepBySTepCreation = false, then this method will display the contents of the queue
     * once after all 20 (element, key) pairs have been added to it.
     *
     * T(n) = O(1). This is because all steps within this method are constant, even the for-loop, as it
     * will always iterate 20 times.
     *
     * @param queue : The Priority Queue in which we add (element, key) pairs to.
     * @param showStepByStepCreation : True or False
     */
    public static void createTwentyQueueStrings(PriorityQueue<String> queue, boolean showStepByStepCreation)
    {
        Random generator = new Random();
        int key;

        String[] elementNames = new String[20];

        elementNames[0] = "Alpha";
        elementNames[1] = "Bravo";
        elementNames[2] = "Charlie";
        elementNames[3] = "Delta";
        elementNames[4] = "Echo";
        elementNames[5] = "Foxtrot";
        elementNames[6] = "Golf";
        elementNames[7] = "Hotel";
        elementNames[8] = "India";
        elementNames[9] = "Juliet";
        elementNames[10] = "Kilo";
        elementNames[11] = "Lima";
        elementNames[12] = "Mike";
        elementNames[13] = "November";
        elementNames[14] = "Oscar";
        elementNames[15] = "Papa";
        elementNames[16] = "Quebec";
        elementNames[17] = "Romeo";
        elementNames[18] = "Sierra";
        elementNames[19] = "Tango";

        //if the user wants to see the contents of the queue after each (element, key) pair is added.
        if(showStepByStepCreation)
        {
            System.out.println("\nStep-By-Step Creation Of The Priority Queue, Which Is A Minimum Heap");
            System.out.print("------------------------------------------------------------------------------");
            for(int i = 0; i < 20; i++)
            {
                key = generator.nextInt(21) + 11; //Randomize the key to be a value between 11-31.
                System.out.printf("\n\n\nAfter Element  %d  Is Inserted Into The Queue (Minimum Heap):", i+1);
                System.out.print("\n----------------------------------------------------------------");
                queue.min_Heap_Insert(elementNames[i], key);
                queue.viewAllElements();

            }

        }
        else
        {
            //If the user just wants to see the contents of the queue after all 20 (element, key) pairs have been added.
            System.out.println("\n\n\nAfter 20 Elements Have Been Inserted Into The Queue (Minimum Heap):");
            System.out.print("----------------------------------------------------------------");
            for(int i = 0; i < 20; i++)
            {
                key = generator.nextInt(21) + 11; //Randomize the key to be a value between 11-31.
                queue.min_Heap_Insert(elementNames[i], key);

            }


            queue.viewAllElements();
        }


    }

    /**
     * This method adds 20 (element, key) pairs to the queue. The elements are Characters.
     * If showStepByStepCreation = true, then this method will display the contents of the queue
     * after each (element, key) pair is added to it.
     * If showStepBySTepCreation = false, then this method will display the contents of the queue
     * once after all 20 (element, key) pairs have been added to it.
     *
     * T(n) = O(1). This is because all steps within this method are constant, even the for-loop, as it
     * will always iterate 20 times.
     * @param queue : The Priority Queue in which we add (element, key) pairs to.
     * @param showStepByStepCreation : True or False
     */
    public static void createTwentyQueueCharacters(PriorityQueue<Character> queue, boolean showStepByStepCreation)
    {
        Random generator = new Random();
        int key;
        char letter = 'A';

        //If the user wants to view the contents of the queue after each (element, key) pair has been added to it.
        if(showStepByStepCreation)
        {
            System.out.println("\nStep-By-Step Creation Of The Priority Queue, Which Is A Minimum Heap");
            System.out.print("------------------------------------------------------------------------------");

            for(int i = 0; i < 20; i++)
            {
                key = generator.nextInt(21) + 11; //Randomly generate key to be a value between 11-31.

                System.out.printf("\n\nAfter Element  %d  Is Inserted Into The Queue (Minimum Heap):", i+1);
                System.out.print("\n----------------------------------------------------------------");
                queue.min_Heap_Insert(letter, key);
                queue.viewAllElements();

                letter = (char)((int)letter + 1);

            }

        }
        else
        {
            //If the user only wants to view the contents of the
            //queue after all (element, key) pairs have been added.
            for(int i = 0; i < 20; i++)
            {
                key = generator.nextInt(21) + 11; //Randomly generate key to be a value between 11-31.

                queue.min_Heap_Insert(letter, key);
                letter = (char)((int)letter + 1);

            }
            System.out.println("\n\n\nAfter 20 Elements Have Been Inserted Into The Queue (Minimum Heap):");
            System.out.print("----------------------------------------------------------------");


            queue.viewAllElements();
        }
    }

    /**
     * This method adds 20 (element, key) pairs to the queue. The elements can be either Integers,
     * Floats, or Doubles.
     * The Type parameter determines which type of elements we are working with.
     * If showStepByStepCreation = true, then this method will display the contents of the queue
     * after each (element, key) pair is added to it.
     * If showStepBySTepCreation = false, then this method will display the contents of the queue
     * once after all 20 (element, key) pairs have been added to it.
     *
     * T(n) = O(1). This is because all steps within this method are constant, even the for-loop, as it
     * will always iterate 20 times.
     * @param queue : The Priority Queue in which we add (element, key) pairs to.
     * @param showStepByStepCreation : True or False
     * @param type : The type of the elements we are working with. For example, if type = 1, then we are working
     *             with integers. If type = 1f, then we are working with floats.
     */
    public static <E> void createTwentyQueueNumbers(PriorityQueue<E> queue, boolean showStepByStepCreation, E type)
    {
        Random generator = new Random();
        int key;

        Object number;

        //Example: if type = 5.
        if(type instanceof Integer)
        {
            number = 100;
        }
        else if(type instanceof Float)
        {
            //Example: if type = 8.7f
            number = 100.0f;
        }
        else //Else it's a Double..Example: if type = 9.10
        {
            number = 100.0;

        }

        //If the user would like to view the contents of the queue after each (element, key) pair has been added
        //to it.
        if(showStepByStepCreation)
        {
            System.out.println("\nStep-By-Step Creation Of The Priority Queue, Which Is A Minimum Heap");
            System.out.print("------------------------------------------------------------------------------");

            for(int i = 0; i < 20; i++)
            {

                key = generator.nextInt(21) + 11;

                System.out.printf("\n\nAfter Element  %d  Is Inserted Into The Queue (Minimum Heap):", i+1);
                System.out.print("\n----------------------------------------------------------------");
                queue.min_Heap_Insert((E)number, key);
                queue.viewAllElements();

                if(number instanceof Integer)
                {
                    number = (int)number + 1;
                }
                else if(number instanceof Float)
                {
                    number = (float)number + 1.1f;
                }
                else //it's an instance of Double
                {
                    number = (double)number + 1.11;
                }

            }


        }
        else
        {
            //If the user only wants to view the contents of the queue after
            //all 20 (element, key) pairs have been added.
            for(int i = 0; i < 20; i++)
            {
                key = generator.nextInt(21) + 11;

                queue.min_Heap_Insert((E)number, key);

                if(number instanceof Integer)
                {
                    number = (int)number + 1;
                }
                else if(number instanceof Float)
                {
                    number = (float)number + 1.1f;
                }
                else //it's an instance of Double
                {
                    number = (double)number + 1.11;
                }

            }
            System.out.println("\n\n\nAfter 20 Elements Have Been Inserted Into The Queue (Minimum Heap):");
            System.out.print("----------------------------------------------------------------");


            queue.viewAllElements();
        }
    }

    /**
     * This method inserts an (element, key) pair into the Priority Queue. The element can be of type Integer,
     * Float, Double, String or Character,
     * but must be the same type as the type of elements accepted by the Priority Queue.
     * This method first asks the user to input the element value and validates said input. If the
     * input is not of the correct type, the user is asked to enter a different value for the element.
     * This method then asks the user to input the key value and validates said input. If the key
     * input is not an integer within the range 11-31, the user is asked to enter a different value for the key.
     *
     * This method will only iterate through 2 while loops. The outer while loop is controlled by the user-inputted
     * key value and the inner while loop is controlled by the user-inputted element value.
     * Thus, T(n) = O(nm) where n is the number of times the key input is invalid and m is the number
     * of times the element input is invalid.
     * @param scanObj : The object used for the standard input stream.
     * @param queue : The Priority Queue to which we are inserting an (element, key) pair into.
     * @param type : Is used to determine which inner while loop we traverse. For example type = 1f indicates
     *             that we iterate through the inner while loop that deals with Float values.
     * @param <E> : Not really a parameter, but indicates that this method accepts generic types.
     */
    public static <E> void insertAnElement(Scanner scanObj, PriorityQueue<E> queue, E type)
    {
        String userInput = "";
        Object element = null;
        int userKeyInput = 31;
        boolean isInputValidType;
        boolean isValidLength = false;

        //Will iterate as long as the user input for the key value is not within the
        //range of 11-31
        //This loop will also iterate as long as the length of the user input for the element
        //is greater than 12 (if the elemnt type is String).
        while(!isValidLength) {
            isInputValidType = false;

            //If the element type is Integer.
            if (type instanceof Integer) {
                while (!isInputValidType) {
                    isInputValidType = true;

                    System.out.print("\n\nPlease Enter The Element You Would Like To Insert: ");
                    userInput = scanObj.nextLine();

                    try {
                        Integer.parseInt(userInput); //Can the user-inputted string be converted to an Integer?
                    } catch (Exception e) {
                        System.err.printf("\nSorry, the input \"%s\" is not valid. Please Enter An Integer Value.\n", userInput);
                        isInputValidType = false;
                    }
                }

                element = Integer.parseInt(userInput);

            } else if (type instanceof Character) {
                //If the element type is Character.
                while (!isInputValidType) {
                    isInputValidType = true;

                    System.out.print("\n\nPlease Enter The Element You Would Like To Insert: ");
                    userInput = scanObj.nextLine();

                    if (userInput.length() != 1) {
                        //If the user-inputted string is not 1 character in length.
                        System.err.printf("\nSorry, the input \"%s\" is not valid. Please Enter A Character Value.\n", userInput);
                        isInputValidType = false;
                    }

                }

                element = (char) userInput.charAt(0);

            } else if (type instanceof Float) {

                //If the element type is Float.
                while (!isInputValidType) {
                    isInputValidType = true;

                    System.out.print("\n\nPlease Enter The Element You Would Like To Insert: ");
                    userInput = scanObj.nextLine();

                    try {
                        Float.parseFloat(userInput); //Can the user-inputted string be converted to a Float?
                    } catch (Exception e) {
                        System.err.printf("\nSorry, the input \"%s\" is not valid. Please Enter A Float Value.\n", userInput);
                        isInputValidType = false;
                    }

                }

                element = Float.parseFloat(userInput);

            } else if (type instanceof Double) {

                //If the element type is Double.
                while (!isInputValidType) {
                    isInputValidType = true;

                    System.out.print("\n\nPlease Enter The Element You Would Like To Insert: ");
                    userInput = scanObj.nextLine();

                    try {
                        Double.parseDouble(userInput); //Can the user-inputted string be converted to a Double?
                    } catch (Exception e) {
                        System.err.printf("\nSorry, the input \"%s\" is not valid. Please Enter A Double Value.\n", userInput);
                        isInputValidType = false;
                    }

                }

                element = Double.parseDouble(userInput);

            } else //If the element type is String.
            {
                System.out.print("\n\nPlease Enter The Element You Would Like To Insert: ");
                userInput = scanObj.nextLine();

                element = userInput;
            }

            //For output formatting purposes, the string cannot be greater than 12 or less than 1 character long.
            if (userInput.length() > 12 || userInput.length() < 1) {
                System.err.printf("\nSorry, the input \"%s\" is not valid. Please Enter A Value With Length of At Most 12.\n", userInput);

            }
            else
            {
                //Get and check user-input for the key value.
                System.out.print("Please Enter The key Of The New Element: ");
                userInput = scanObj.nextLine();
                try
                {
                    userKeyInput = Integer.parseInt(userInput); //Can the user-inputted string be converted to Integer?

                    isValidLength = true;


                }
                catch(Exception e)
                {
                    System.err.println("ERROR: Key Values Must Be Integers Between 11-31.");
                }

                //Is the user-inputted key value within the range 11-31?
                if(userKeyInput < 11 || userKeyInput > 31)
                {
                    System.err.println("ERROR: Key Values Must Be Integers Between 11-31.");
                    isValidLength = false;
                }

            }

        }

        //Insert the (element, key) pair into the Priority Queue.
        queue.min_Heap_Insert((E)element, userKeyInput);

        //View the contents of the queue.
        System.out.println("\n\nAfter Inserting The Element \"" + element + "\", The Contents Of The Priority Queue Are:");
        System.out.println("----------------------------------------------------------------------------------");
        queue.viewAllElements();

    }

    /**
     * This method asks for the index of the (element, key) pair that should be deleted from the queue.
     * It then validates this user-inputted index value. If the value in not valid,
     * then the user is asked to enter a different index value.
     * If the index is valid, then the (element, key) pair at the specified index is deleted
     * from the Priority Queue. The contents of the queue are then displayed on screen.
     *
     * T(n) = O(n + m), where n is the number of times the user inputs erroneous index values
     * and m is the number of elements in the queue. We also call min_Heap_Delete(), which runs in
     * O(lg(m)) time. min_Heap_Delete() is called within a while loop. However,
     * min_Heap_Delete() will simply execute constants steps if the user-input is not valid.
     * So, min_Heap_Delete() will only run O(lg(m)) when the user inputs a valid value for the index.
     * So, This algorithm runs in O(n + m + lg(m)). Since O(m) is asymptotically larger than O(lg(m)), this algorithm
     * runs in O(n + m) time.
     *
     * @param scanObj : The object that handles the standard input stream.
     * @param queue : The Priority Queue in which we are deleting an (element, key) pair from.
     * @param <E> : This method supports generic typing.
     */
    public static <E> void deleteAnElement(Scanner scanObj, PriorityQueue<E> queue)
    {

        if(queue.getSize() + 1 <= 0) //If the queue is empty.
        {
            System.err.println("\nCannot Delete Any Elements Because The Queue Is Empty!\n");
        }
        else {
            String userInput = "1";
            int indexToDelete = -1;
            boolean isInputValid = false;

            //Will iterate as long as the user inputs erroneous values for the index.
            while (!isInputValid) {
                System.out.print("\nPlease Enter The Index Of The Element You Would Like To Delete: ");
                userInput = scanObj.nextLine();

                try {
                    indexToDelete = Integer.parseInt(userInput); //Can the user-inputted string be converted to Integer?

                    isInputValid = true;
                } catch (Exception e) {
                    System.err.println("\nERROR: The Selected Index Must Be An Integer. Please Try Again.\n");
                }

                //Delete the (element, key) pair at the given index.
                if (!queue.min_Heap_Delete(indexToDelete)) {
                    System.err.println("\nERROR: The Selected Index Must Be Within The Range Of Indices Found In The Queue. Please Try Again.\n");
                    isInputValid = false;
                }

            }

            //Display the contents of the queue after the deletion.
            System.out.printf("\n\nAfter Deleting The Element At Index %d, The Contents Of The Priority Queue Are:", indexToDelete);
            System.out.println("\n-------------------------------------------------------------------------------");
            queue.viewAllElements();
        }


    }

    /**
     * This method extracts the (element, key) pair at index 0 of the Priority Queue.
     * It then displays the element and key values to the screen.
     *
     * T(n) = O(lg(n)), where n is the number of (element, key) pairs in the queue.
     * This time complexity stems from the fact that this method calls extract_Max_Priority_Element(),
     * which runs in O(lg(n)) time.
     *
     * @param queue : The priority queue that we are extracting the root node from.
     * @param <E> : This method supports generic typing.
     */
    public static <E> void extractMaxPriorityElement(PriorityQueue<E> queue)
    {
        //If the queue is empty.
        if(queue.getSize() + 1 <= 0)
        {
            System.err.println("\nCannot Extract An Element Because The Queue Is Empty!\n");
        }
        else
        {
            //maxPriorityElem[0] will hold the extracted element value.
            //maxPriorityElem[1] will hold the extracted key value.
            Object[] maxPriorityElem;

            //Extract the root node.
            maxPriorityElem = queue.extract_Max_Priority_Element();

            //Display the extracted element and key pair.
            System.out.println("\n\nThe Max Priority Element \"" + maxPriorityElem[0] + "\" Has Been Extracted!");
            System.out.println("It Had A Priority Key Of " + maxPriorityElem[1] + ".");

        }


    }

    /**
     * This method asks the user for the index of the (element, key) pair whose key value should change.
     * This input is validated in a while loop. This method then asks the user if she would like to
     * increase or decrease the key. This input is validated in a while loop. This method then asks
     * the user for the new value of the key. This input is validated in a while loop.
     *
     * The new key value then replaces the old key value within the (element, key) pair and
     * the contents of the queue are displayed.
     *
     *
     * T(n) = O(n + m), where n is the number of (element, key) pairs in the queue and m
     * is the number of times the user inputs erroneous values.
     * To be more specific, O(n + p + q + r + lg(n)). n = number of (element, key) pairs in the queue.
     * p = number of times the user inputs erroneous index values. q = number of times the user
     * inputs erroneous values as to whether or not the key should be increased or decreased.
     * r = number of times the user enters erroneous input for the new key value.
     * lg(n) = the time complexity of heap_Increase_Key() or heap_Decrease_Key().
     * Both heap_Increase_Key() and heap_Decrease_Key() are called within the third while loop.
     * However, they will run in O(1) time when the key input is invalid and will only run in
     * O(lg(n)) time when the key input is valid, which exits the while loop. So they only run
     * O(lg(n)) time once. Thus, since O(n) > O(lg(n)), O(lg(n)) can be disregarded.
     * Now, if we say that m = p + q + r, such that m is the number of times the user
     * inputs erroneous values in total, then this alrgotihm runs in O(n + m) time.
     *
     *
     * @param scanObj : The object that handles the standard input stream.
     * @param queue : The queue that we are changing a key value in.
     * @param <E> : This method supports generic typing.
     */
    public static <E> void changeElementPriority(Scanner scanObj, PriorityQueue<E> queue)
    {
        //If the queue is empty.
        if(queue.getSize() + 1 <= 0)
        {
            System.err.println("\nCannot Change An Element's Priority Because The Queue Is Empty!\n");
        }
        else
        {
            boolean isInputValid = false;
            String userInput = "";
            int index = -99;
            int increaseOrDecrease = 0;
            int newKey = 0;

            //Will iterate as long as the user inputs invalid values for the index.
            while(!isInputValid)
            {
                System.out.print("\n\nPlease Enter The Index Of The Element Whose Key You Wish To Change: ");
                userInput = scanObj.nextLine();

                try {
                    index = Integer.parseInt(userInput); //Can the user-inputted string be converted to an Integer?

                    isInputValid = true;

                    //Is there a node in the queue with that index?
                    if(index < 0 || index > queue.getSize())
                    {
                        System.err.printf("ERROR: \"%s\" Is Not Within The Range Of Indices In The Queue. Please Try Again.\n", userInput);
                        isInputValid = false;
                    }
                }
                catch(Exception e)
                {
                    System.err.printf("ERROR: \"%s\" Is Not A Valid Index. Please Enter An Integer Value.\n", userInput);
                }

            }

            isInputValid = false;

            //This will iterate as long as the user inputs invalid values as to whether
            //the key should be increased or decreased.
            while(!isInputValid)
            {
                System.out.println("\n\nWhat Would You Like To Do To The Element's Key?");
                System.out.println("-------------------------------------------------");
                System.out.println("1. Increase The Key.\n2. Decrease The Key.");

                System.out.print("\nYou Choose Option: ");
                userInput = scanObj.nextLine();

                if(!userInput.equals("1") && !userInput.equals("2"))
                {
                    System.err.println("\nERROR: You Must Choose Either \"1\" For Key Increase or \"2\" For Key Decrease.\n");
                }
                else
                {
                    increaseOrDecrease = (int)userInput.charAt(0) - 48;
                    isInputValid = true;
                }
            }

            isInputValid = false;

            //Will iterate as long as the user inputs invalid values for the new key value.
            while(!isInputValid)
            {
                if(increaseOrDecrease == 1)
                {
                    System.out.print("\nYou Are Increasing The Key. Please Enter The New Value Of The Key: ");
                }
                else
                {
                    System.out.print("\nYou Are Decreasing The Key. Please Enter The New Value Of The Key: ");
                }

                userInput = scanObj.nextLine();

                try
                {
                    newKey = Integer.parseInt(userInput); //Can the user-inputted string be converted to Integer?

                    if(increaseOrDecrease == 1) //if we are increasing the key
                    {
                        //Call heap_Increase_Key(). If the new key value is valid, isInputValid = true.
                        //If the new key is invalid, isInputValid = false.
                        isInputValid = queue.heap_Increase_Key(index, newKey);
                        if(!isInputValid)
                        {
                            System.err.println("\nERROR at heap_Increase_Key(int i, int key): New key Must Be Larger Than The Current Key" +
                                    " And Must Be Between 11-31.\n");
                        }
                    }
                    else //Decreasing the key
                    {
                        //Call heap_Decrease_Key(). If the new key value is valid, isInputValid = true.
                        //If the new key is invalid, isInputValid = false.
                        isInputValid = queue.heap_Decrease_Key(index, newKey);
                        if(!isInputValid)
                        {
                            System.err.println("\nERROR at heap_Decrease_Key(int i, int key): " +
                                    "New key Must Be Smaller than the current key And Must Be Between 11-31.\n");

                        }
                    }

                }
                catch(Exception e)
                {
                    System.err.println("\nError: The New Key Value Must Be An Integer. Please Try Again.\n");
                }

            }

            if(increaseOrDecrease == 1)
            {
                System.out.print("\n\nAfter Increasing ");
            }
            else
            {
                System.out.print("\n\nAfter Decreasing ");
            }


            System.out.println("The Element At Index "+ index + "'s Key To " + newKey + ", The Contents Of The Priority Queue Are:");
            System.out.println("-----------------------------------------------------------------------------------------------");
            //Show the contents of the queue.
            queue.viewAllElements();
        }
    }



}



