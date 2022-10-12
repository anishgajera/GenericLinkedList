package com.company;
import java.util.NoSuchElementException;

/*

    Written by Anish Gajera on 9/24/2022 for CS 3345.001
 */

public class GenLinkedList<T> //linked list with generic type T
{
    private Node<T> head;
    private Node<T> tail;
    int size = 0;

    //class Node - inner class which creates/assigns new nodes as necessary
    private static class Node<T>
    {
        T data;
        Node<T> next;

        Node(T data, Node<T> n)
        {
            this.data = data;
            this.next = n;
        }
    }

    /*
        a. addFront method - receives an item to add as a parameter, and adds it to front of the list
     */
    public void addFront(T d)
    {
        //if the list is empty
        if (head == null)
        {
            //add a new head node
            head = new Node<T>(d, null);
            //tail will be head node since only one node in list
            tail = head;
        }
        else
        {
            //else, set head to new node
            head = new Node<T>(d, head);
        }
        //increment size since we are adding to the list
        size++;
    }

    /*
        b. addEnd method - receives an item to add as a parameter, and adds it to the back of the list
     */
    public void addEnd(T d)
    {
        //if the list is empty
        if(head == null)
        {
            //add a new head node
            head = new Node<T>(d, head);
            //tail will be head node since only one node in list
            tail = head;
        }
        else
        {
            //else, the original tail node will be a new node pointing to null
            tail.next = new Node<T>(d, null);
            //current tail/end node will become tail.next
            tail = tail.next;
        }
        //increment size since we are adding to the list
        size++;
    }

    /*
        c. removeFront method - removes an item from the front of the list
     */
    public T removeFront()
    {
        //variable olddata will hold the old data that is removed from the front of the list
        T olddata;

        //if the list is empty to begin with
        if(head == null)
        {
            //throw exception
            throw new NoSuchElementException();
        }
        else if(head == tail) //else if the list has only one node/element
        {
            //set olddata to the head node
            olddata = head.data;
            //replace head and tail node (same node in this case) with null
            head = null;
            tail = null;
        }
        else //else if the list has more than one element
        {
            //set olddata to the current head's data
            olddata = head.data;
            //new head will be head.next
            head = head.next;
        }
        //decrement the size since we are removing from the list
        size--;
        //return the olddata (i.e. data of item we removed)
        return olddata;
    }

    /*
        d. removeEnd method - removes an element from the end of the list
     */
    public T removeEnd()
    {
        //variable olddata will hold the old data that is removed from the front of the list
        T olddata;

        //if the list is empty
        if (head == null)
        {
            //throw exception
            throw new NoSuchElementException();
        }
        else if(head == tail) //else is the list has only one node/element
        {
            //set olddata to the head node
            olddata = head.data;
            //set the head and tail node (same one in this case) to null
            head = null;
            tail = null;
        }
        else //else if the list has more than one element
        {
            //create a new node and set it to head
            Node<T> p = head;
            //while you havent reached the tail
            while(p.next != tail)
            {
                //keep traversing the list till you reach the tail node
                p = p.next;
            }
            //once p == tail, then set olddata to tail's data
            olddata = tail.data;
            //set p.next to null
            p.next = null;
            //set tail to p (which is pointing to null)
            tail = p;
        }
        //decrement the size since we are removing from the list
        size--;
        //return the olddata (i.e. data of item we removed)
        return olddata;
    }

    /*
        e. set method - receives a position and item as parameters,
        sets the element at this position, provided it is within the current size
     */
    public void set(int pos, T item)
    {
        //if the position is invalid (less than 0 or out of bounds)
        if (pos < 0 || pos > size - 1)
        {
            //throw exception
            throw new ArrayIndexOutOfBoundsException("Index " + pos + "size " + size);
        }

        //create new node and set it to head
        Node<T> p = head;

        //traverse the linked list till you get to the requested position
        for(int i = 0; i < pos; i++)
        {
            p = p.next;
        }

        //set that node's data to the item you provided as parameter
        p.data = item;
    }


    /*
        f. get method - receives s position as a parameter, returns the item at this
        position, provided it is within the current size
     */
    public Node<T> get(int pos)
    {
        //if the position is invalid (less than 0 or out of bounds)
        if (pos < 0 || pos > size - 1)
        {
            //throw exception
            throw new ArrayIndexOutOfBoundsException("Index " + pos + "size " + size);
        }

        //create new node and set it to head
        Node<T> p = head;
        //traverse the linked list till you get to the requested position
        for(int i = 0; i < pos; i++)
        {
            p = p.next;
        }

        //once position is reached from previous loop and p is set to requested position
        //return p.data (i.e. the data of the requested position)
        return (Node<T>) p;
    }

    /*
        g. swap method - receives two index positions as parameters, and swaps the nodes
        at these positions (not just the values inside the nodes), provided both
        positions are within the current size
     */
    public void swap(int pos1, int pos2)
    {
        //if first position is invalid (less than 0 or out of bounds)
        if(pos1 < 0 || pos1 > size - 1)
        {
            //throw exception
            throw new ArrayIndexOutOfBoundsException("Index " + pos1 + "size " + size);
        }
        //if second position is invalid (less than 0 or out of bounds)
        if(pos2 < 0 || pos2 > size - 1)
        {
            //throw exception
            throw new ArrayIndexOutOfBoundsException("Index " + pos2 + "size " + size);
        }

        //temp nodes (utilized for list traversal and swap)
        Node<T> p1 = head;
        Node<T> p2 = head;
        //previous nodes for the 2 above temp nodes
        Node<T> pp1 = head;
        Node<T> pp2 = head;

        //previous of p1
        for(int i = 0; i < pos1 - 1; i++)
        {
            pp1 = pp1.next;
        }
        //node p1
        for(int i = 0; i < pos1; i++)
        {
            p1 = p1.next;
        }
        //previous of p2
        for(int i = 0; i < pos2 - 1; i++)
        {
            pp2 = pp2.next;
        }
        //node p2
        for(int i = 0; i < pos2; i++)
        {
            p2 = p2.next;
        }

        //make the previous of p1 point to p2
        pp1.next = p2;
        //make the previous of p2 point to p1
        pp2.next = p1;

        //swap the nodes
        Node<T> temp = null;
        temp = p1.next;
        p1.next = p2.next;
        p2.next = temp;
    }

    /*
        h. shift method - receives an integer as a parameter, and shifts the list forward or
        backward this number of nodes, provided it is within the current size
           1,2,3,4,5    shifted +2    3,4,5,1,2
           1,2,3,4,5    shifted -1    5,1,2,3,4
     */
    public void shift(int i)
    {
        //if shift value is 0, then we are done shifting
        if(i == 0)
        {
            //return
            return;
        }
        //if size is 0 (i.e. no list)
        if(size == 0)
        {
            //return
            return;
        }
        //else if i value is positive, we shift left by i
        else if(i > 0)
        {
            //traverse through list to shift
            for(int x = i; x != 0; x--)
            {
                //set temp to head
                Node<T> temp = head;
                Node<T> end;
                //set head to the next node
                head = head.next;
                //end node will be the node at size - 2
                end = get(size - 2);
                end.next = temp;
                //set temp to null now
                temp.next = null;
            }
        }
        else//if value is negative, we shift right by i
        {
            //traverse
            for(int x = i; x != 0; x++)
            {
                //same concept as above
                Node<T> temp = head;
                Node<T> end = get(size - 2);
                head = get(size - 1);
                head.next = temp;
                end.next=null;
            }
        }
    }

    /*
        i. removeMatching method - receives a value of the generic type as a
        parameter and removes all occurrences of this value from the list.
     */
    public void removeMatching(T value)
    {
        //if the list is empty, there is nothing to remove
        if (head == null)
        {
            //throw exception
            throw new NoSuchElementException();
        }
        //p1 is where we start our search for duplicates
        Node<T> p1 = head;
        //pp1 is the previous of p1
        Node<T> pp1 = head;
        //if if the data at p1 is the value we want to remove
        if (p1.data == value){
            //set head to the next node
            head = p1.next;
            //set p1 to head since head is the next node now
            p1 = head;
            //decrement the size since we removed
            size--;
        }

        //traverse the list
        for(int i = 0; i < size && p1 != null; i++)
        {
            //if the data at p1 is the value we want to remove
            if (p1.data == value)
            {
                //point the previous over the one we want to remove(ignore it; garbage collector will remove it)
                pp1.next = p1.next;
                //set p1 to the next node
                p1 = p1.next;
                //decrement the size
                size--;
            }
            else
            {
                //pp1 will be p1
                pp1 = p1;
                //and p1 will point to the next node after p1
                p1 = p1.next;
            }
        }
    }

    /*
        j. erase method - receives an index position and number of elements
        as parameters, and removes elements beginning at the index position
        for the number of elements specified, provided the index position is
        within the size and together with the number of elements
        does not exceed the size
     */
    public void erase(int i, int numElements)
    {
        //if index value is invalid
        if(i < 0 || i > size - 1)
        {
            //throw exception
            throw new ArrayIndexOutOfBoundsException("Index " + i + "size " + size);
        }
        //if number of elements to remove is invalid value
        if(numElements < 0 || numElements > size - 1)
        {
            //throw exception
            throw new ArrayIndexOutOfBoundsException("Index " + numElements + "size " + size);
        }

        //p1 is index node we want to start removal from
        Node<T> p1 = head;
        //p2 is the last node in the range of nodes we want to remove
        Node<T> p2 = head;
        //pp1 is the previous node of p1 (start removal node)
        Node<T> pp1 = head;

        //set p1
        for(int j = 0; j < i; j++)
        {
            p1 = p1.next;
        }
        //set pp1
        for(int j = 0; j < i - 1; j++)
        {
            pp1 = pp1.next;
        }
        //set p2
        for(int j = 0; j < numElements; j++)
        {
            p2 = p2.next;
        }

        //set pp1.next to point to p2.next (garbage collector will ignore the middle range (from i to numElements), hence erasing)
        pp1.next = p2.next;
    }

    /*
        insertList method - receives a generic List (a Java List) and an index position
        as parameters, and copies each value of the passed list into the current list
        starting at the index position, provided the index position does not exceed the size.
        For example, if list has a,b,c and another list having 1,2,3 is inserted at
        position 2, the list becomes a,b,1,2,3,c
     */
    public void insertList(GenLinkedList<T> list, int i)
    {
        //if index value is invalid
        if (i < 0 || i > size - 1)
        {
            //throw exception
            throw new ArrayIndexOutOfBoundsException();
        }

        //if the list is initially empty
        if (head == null)
        {
            //set head of current list to head of list to be inserted
            head = list.head;
        }
        //if the insert position is the end of the current list
        else if(i == size - 1)
        {
            //set the new tail to point to the head of the original list
            tail.next = list.head;
        }
        //else if the index value to be inserted at is anything else
        else
        {
            //node at which insertion will occur (current node)
            Node<T> p1 = head;
            //previous node of node of insertion (p1)
            Node<T> pp1 = head;

            //set previous node
            for (int j = 0; j < i - 1; j++)
            {
                pp1 = pp1.next;
            }
            //set current node
            for (int j = 0; j < i; j++)
            {
                p1 = p1.next;
            }

            //previous node of initial list will point to the head of new list
            pp1.next = list.head;
            //tail of inserted list will point to node at which insertion was supposed to occur
            list.tail.next = p1;
        }
    }

    //toString method
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[ ");

        Node<T> p = head;
        while (p != null)
        {
            sb.append(p.data + " ");
            p = p.next;
        }

        sb.append("]");

        return new String(sb);
    }


    //main function
    public static void main(String[] args)
    {
        GenLinkedList<Integer> list = new GenLinkedList<>();
        System.out.println("addFront implementation");
        for(int i = 0; i < 5; i++)
        {
            list.addFront(i);
        }
        System.out.print(list);
        System.out.println("\naddEnd implementation");
        for(int i = 0; i < 5; i++)
        {
            list.addEnd(i);
        }
        System.out.print(list);
        System.out.println("\nremoveFront implementation");
        list.removeFront();
        System.out.print(list);
        System.out.println("\nremoveEnd implementation");
        list.removeEnd();
        System.out.print(list);
        System.out.println("\nset implementation");
        list.set(2, 5);
        System.out.print(list);
        System.out.println("\nget implementation");
        Node value = list.get(2);
        System.out.print(value.data);
        System.out.println("\nswap implementation");
        list.swap(2, 3);
        System.out.print(list);
        System.out.println("\nshift implementation");
        list.shift(2);
        System.out.print(list);
        System.out.println("\nremoveMatching implementation");
        list.removeMatching(0);
        System.out.print(list);
        System.out.println("\nerase implementation");
        list.erase(2, 5);
        System.out.print(list);
        System.out.println("\ninsertList implementation");
        GenLinkedList<Integer> list2 = new GenLinkedList<>();
        for(int i = 6; i < 11; i++)
        {
            list2.addEnd(i);
        }
        list.insertList(list2, 2);
        System.out.print(list);
    }
}
