package multiCalculator;

public class prev_next {
	// Node creation
    public Node head;
    public Node curr;
    
    String[] page_arr=new String[100];
    
    private int indx=0;
    
    // Storing page in node
    class Node{
        String data;
        Node next;
        Node prev;
        Node(String currData){
            this.data=currData;
            this.next=null;
            this.prev=null;
        }
    }
    
    // Converting page array to doubly linked list
    prev_next(String[] pages){
    	int i=0;
    	while(pages[i]!=null) {
        	Node newNode=new Node(pages[i]);
        	if(curr==null) {
        		head=newNode;
                curr=newNode;
        	}
        	else {
        		newNode.prev=curr;
            	curr.next=newNode;
            	
                curr=newNode;
        	}
        	i++;
        }
    }
    
    // Calling function for previous or next page
    public void call(String data, int indx) {
    	switch(data) {
    		case "main":{
    			Multi_Calculator Cal=new Multi_Calculator(true, page_arr, indx);
				Cal.frame.setVisible(true);
    			break;
    		}
    		case "cal":{
    			Calculator Cal=new Calculator(true, page_arr, indx);
				Cal.frame.setVisible(true);
    			break;
    		}
    		case "standrd":{
    			Standard_Cal Cal=new Standard_Cal(true, page_arr, indx);
				Cal.frame.setVisible(true);
    			break;
    		}
    		case "scientific":{
    			Scientific_Cal Cal=new Scientific_Cal(true, page_arr, indx);
				Cal.frame.setVisible(true);
    			break;
    		}
    		case "IP":{
    			Network_Calculator Cal=new Network_Calculator(true, page_arr, indx);
				Cal.frame.setVisible(true);
    			break;
    		}
    		case "BMI":{
    			BMI Cal=new BMI(true, page_arr, indx);
				Cal.frame.setVisible(true);
    			break;
    		}
    		case "distance":{
    			Distance_Speed_Time_Cal Cal=new Distance_Speed_Time_Cal(true, page_arr, indx);
				Cal.frame.setVisible(true);
    			break;
    		}
    		case "cnvrtr":{
    			Converter Con=new Converter(true, page_arr, indx);
				Con.frame.setVisible(true);
    			break;
    		}
    		case "Weight":{
    			Weight_Converter Con=new Weight_Converter(true, page_arr, indx);
    			Con.frame.setVisible(true);
    			break;
    		}
    		case "length":{
    			Length_Converter Con=new Length_Converter(true, page_arr, indx);
    			Con.frame.setVisible(true);
    			break;
    		}
    		case "Temp":{
    			Temp_Converter Con=new Temp_Converter(true, page_arr, indx);
    			Con.frame.setVisible(true);
    			break;
    		}
    		case "Time":{
    			Time_Converter Con=new Time_Converter(true, page_arr, indx);
				Con.frame.setVisible(true);
    			break;
    		}
    		case "angle":{
    			Angle_Converter Con=new Angle_Converter(true, page_arr, indx);
    			Con.frame.setVisible(true);
    			break;
    		}
    		case "Data":{
    			Data_Converter Con=new Data_Converter(true, page_arr, indx);
    			Con.frame.setVisible(true);
    			break;
    		}
    	}
    }
    
    // Return current index of storing page
    public int curr_index() {
    	return indx;
    }

    // Add page after the current page
    public String[] add(Boolean prev_nxt_clicked, String currData, int index){
    	if(prev_nxt_clicked!=true) {
    		Node newnode=new Node(currData);
            if(head==null){
                head=newnode;
                curr=newnode;
                indx=index+1;
            }
            else{
            	int i=0;
            	Node currNode=head;
            	while(currNode!=null) {
            		if(i==index)
            			break;
            		else
            			currNode=currNode.next;
            		i++;
            	}
                
                newnode.prev=currNode;
                currNode.next=newnode;
                
                curr=newnode;
                
                indx=index+1;
            }
    	}
    	
    	// Storing doubly linked list elements to array
    	Node currNode=head;
    	int i=0;
        while(currNode!=null) {
        	page_arr[i++]=currNode.data;
        	currNode=currNode.next;
        }
        
    	return page_arr;
    }

    // Calling previous page
    public void prevbtn(int index){
    	int i=0;
    	
    	// Storing doubly linked list elements to array
    	Node currNode=head;
    	while(currNode!=null) {
        	page_arr[i++]=currNode.data;
        	currNode=currNode.next;
        }
    	
    	int j=0;
    	
    	// Finding current page from list
    	Node currNode1=head;
    	while(currNode1!=null) {
    		if(j==index)
    			break;
    		else
    			currNode1=currNode1.next;
    		j++;
    	}
    	
    	currNode1=currNode1.prev;
    	
    	indx=index-1;
    	
    	call(currNode1.data, indx);
    }
    
    // Calling Next page
    public void nextbtn(int index){
    	int i=0;
    	
    	// Storing doubly linked list elements to array
    	Node currNode=head;
    	while(currNode!=null) {
        	page_arr[i++]=currNode.data;
        	currNode=currNode.next;
        }
    	
    	int j=0;
    	
    	// Finding current page from list
    	Node currNode1=head;
    	while(currNode1!=null) {
    		if(j==index)
    			break;
    		else
    			currNode1=currNode1.next;
    		j++;
    	}
    	
    	currNode1=currNode1.next;
    	
    	indx=index+1;
    	
    	call(currNode1.data, indx);
    }
    
    // Return Is previous page exist
    public Boolean prevExist(int index){
    	int i=0;
    	
    	// Storing doubly linked list elements to array
    	Node currNode=head;
        while(currNode!=null) {
        	page_arr[i++]=currNode.data;
        	currNode=currNode.next;
        }
        
        int j=0;
        
        // Finding current page from list
        Node currNode1=head;
    	while(currNode1!=null) {
    		if(j==index)
    			break;
    		else
    			currNode1=currNode1.next;
    		j++;
    	}
    	
    	if(currNode1.prev!=null)
    		return true;
    	else
    		return false;
    }
    
    // Return Is next page exist
    public Boolean nextExist(int index){
    	int i=0;
    	
    	// Storing doubly linked list elements to array
    	Node currNode=head;
        while(currNode!=null) {
        	page_arr[i++]=currNode.data;
        	currNode=currNode.next;
        }
        
        int j=0;
        
        // Finding current page from list
        Node currNode1=head;
    	while(currNode1!=null) {
    		if(j==index)
    			break;
    		else
    			currNode1=currNode1.next;
    		j++;
    	}
    	
    	if(currNode1.next!=null) {
    		if(currNode1.next.data.equals("Bin_IP") || currNode1.next.data.equals("Dec_IP"))
        		return false;
    		else
    			return true;
    	}	
    	else
    		return false;
    }
}