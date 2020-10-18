<!DOCTYPE html>
<head>


</head>

<body>
  
   <script>

    
      var array_list_name_raw = '${StateMachineList}';
      var array_list_name_replace = array_list_name_raw.replace("[","");
      var array_list_name_replace_2 = array_list_name_replace.replace("]","");
      var list_name = array_list_name_replace_2.split(",");
     

      var ARN_name = '${StateMachineListARN}';
      var ARN_name_1 = ARN_name.replace("[","");
      var ARN_name_2 = ARN_name_1.replace("]","");
      var ARN_name = ARN_name_2.split(",");
     

      var Status_name = '${StateMachineStatus}';
      var Status_name_1 = Status_name.replace("[","");
      var Status_name_2 = Status_name_1.replace("]","");
      var Status_name =Status_name_2.split(",");
      console.log(Status_name);

      // call for loop with list
      var table = document.createElement('table');
      table.border = "1";
      var th = document.createElement('th');
      var header_name = th.appendChild(document.createTextNode("Name"));
      var th2 = document.createElement('th');
      var header_ARN = th2.appendChild(document.createTextNode("ARN"));
      var th3 = document.createElement('th');
      var header_status= th3.appendChild(document.createTextNode("Status"));
      

      table.appendChild(th);
      table.appendChild(th2);
      table.appendChild(th3);
      for (var i = 0; i < list_name.length; i++){
         var tr = document.createElement('tr');   

         var td1 = document.createElement('td');
         var td2 = document.createElement('td');
         var td3 = document.createElement('td');
         var delete_td  = document.createElement("a");
         var td4 = document.createElement('td');

         var name_td = document.createElement('a');

         var text1         = document.createTextNode(list_name[i]);
         var text2         = document.createTextNode(ARN_name[i]);
         var text3         = document.createTextNode("delete")
         var text4         = document.createTextNode(Status_name[i]);

         if(Status_name[i]== "DELETING")
         {

         }
         else{
         delete_td.setAttribute("href","deletestate/" + ARN_name[i]);
         name_td.setAttribute("href","seeStateMachine/" + ARN_name[i]);
         //delete_td.setAttribute("href","/aws");
         }
         

         tr.appendChild(td1);
         td1.appendChild(name_td);
         name_td.appendChild(text1);
         tr.appendChild(td2); 
         td2.appendChild(text2);
         tr.appendChild(td3);
         tr.appendChild(td3);
         td3.appendChild(text4);
         tr.appendChild(td4);
         td4.appendChild(delete_td);
         delete_td.appendChild(text3);
         
         
         table.appendChild(tr);
      }
     
      document.body.appendChild(table);

      //display names in table
   </script>
   <form action = "/aws">
   <button type = "submit">Create new StateMachine</button>
   </form>
</body>