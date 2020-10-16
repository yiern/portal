<!DOCTYPE html>
<html lang="">

<head>
    <csrf disabled="true"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <script src="js/jquery.js"></script>
    <script src = "js/codemirror.js"></script>
    <script src = js/javascript.js></script>
   
    <link rel="stylesheet" type = "text/css" href = "css/elegant.css">
    <link rel="stylesheet" type = "text/css" href = "css/darcula.css">
    <link rel="stylesheet" type="text/css" href="css/graph.css">
    <link rel="stylesheet" type="text/css" href="css/codemirror.css">
    <script type="text/javascript" src="js/sfn.js"></script>
</head>

<body>
Name:<input type="text" id = "nameText" required> <p id = "errorLabel"></p>
<textarea id="userInput"></textarea>
<button id="definition_button">Create StateMachine</button>

<div id="graph-914" class="workflowgraph">
        <div class="graph-legend">
            <ul>
                <li>
                    <div class="success"></div>
                    <span>Success</span>
                </li>
                <li>
                    <div class="failed"></div>
                    <span>Failed</span>
                </li>
                <li>
                    <div class="cancelled"></div>
                    <span>Cancelled</span>
                </li>
                <li>
                    <div class="in-progress"></div>
                    <span>In Progress</span>
                </li>
                <li>
                    <div class="caught-error"></div>
                    <span>Caught Error</span>
                </li>
            </ul></div><svg></svg></div>
</body>

</html>
<script>
  var editor = CodeMirror.fromTextArea(document.getElementById("userInput"), {
    lineNumbers: true,
    mode:{name: "javascript", json: true},
    matchBrackets: true,
    theme:"darcula"
  });
</script>

<script type="text/javascript">
    $(document).ready(function() 
    {
        var element = document.getElementById('graph-914')
        var options = 
        {
            width: parseFloat(getComputedStyle(element, null).width.replace("px", "/")),
            height: 1000,
            layout: 'LR',
            resizeHeight: true,
        };

        /* only once definition from console */
		$( "#definition_button" ).on( "click", function(){
       
            var userDefinition = editor.getValue();
            try
            {
                var definition = JSON.parse(userDefinition);
                var JSONstring = JSON.stringify(definition);
                var Name_value = document.getElementById("nameText").value;

                if(Name_value == ""|| Name_value == null)
                {
                    console.log("no name")
                    document.getElementById("errorLabel").innerHTML= "No name";
                }
                else
                {
                    console.log(Name_value);
                    $.post("/awsget", { jsonData: JSONstring , p_name: Name_value});
                
                }
            }
            catch(e)
            {
                document.getElementById("errorLabel").innerHTML = "Error: Invalid Json Format";
            }
        });
       
        editor.on("change",function(){
           
            
            var userDefinition = editor.getValue();
            var definition = JSON.parse(userDefinition);
           
           
            var elementId = '#graph-914';
            /* sample of recurring events from output from java program */
            var events = {
                "events": []  };


            var graph = new sfn.StateMachineExecutionGraph(definition, events, elementId, options);
            //console.log( events);
            graph.render();
        
        
        });
    });

</script>
