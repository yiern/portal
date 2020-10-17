<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <script src="/js/jquery.js"></script>
    <script src = "/js/codemirror.js"></script>
    <script src = "/js/javascript.js"></script>
    <script src = "/js/jquery.redirect.js"></script>
   
    <link rel="stylesheet" type = "text/css" href = "/css/elegant.css">
    <link rel="stylesheet" type = "text/css" href = "/css/darcula.css">
    <link rel="stylesheet" type="text/css" href="/css/graph.css">
    <link rel="stylesheet" type="text/css" href="/css/codemirror.css">
    <script type="text/javascript" src="/js/sfn.js"></script>
</head>
<body>
    <textarea id = "userInput"></textarea><br>
   
    <button type = "submit" onclick="saveState()">Save</button><br>
    
    
    <textarea id = "executionInput"></textarea><br>
    <button type = "submit" onclick="runExecution()">Run execution</button>
    
    <div>
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
    <script>

       var definition = '${definition}';
        console.log(JSON.stringify (definition));
    
        var editor = CodeMirror.fromTextArea(document.getElementById("userInput"), {
        lineNumbers: true,
        mode:{name: "javascript", json: true},
        matchBrackets: true,
        theme:"darcula",
        });
        editor.getDoc().setValue(JSON.stringify(definition,null,2));
        
        var element = document.getElementById('graph-914')
        var options = 
        {
            width: parseFloat(getComputedStyle(element, null).width.replace("px", "/")),
            height: 1000,
            layout: 'LR',
            resizeHeight: true,
        };

        var input_editor = CodeMirror.fromTextArea(document.getElementById("executionInput"),{
            lineNumbers: true,
        mode:{name: "javascript", json: true},
        matchBrackets: true,
        theme:"darcula",
        });
           
            
        var userDefinition = editor.getValue();
        var definition = JSON.parse(userDefinition);
        
        
        var elementId = '#graph-914';
        /* sample of recurring events from output from java program */
        var events = {
            "events": []  };

        var graph = new sfn.StateMachineExecutionGraph(definition, events, elementId, options);
        graph.render();


        editor.on("change", function(){
            var userDefinition = editor.getValue();
            var definition = JSON.parse(userDefinition);
            var graph = new sfn.StateMachineExecutionGraph(definition, events, elementId, options);
            graph.render();

        })
       
        function runExecution()
        {
            var value = input_editor.getValue();
            var ARN = '${ARN}';
        

                $.redirect('/runExecution', {'input': value, 'arn': ARN, 'definition' : JSON.stringify(definition)}, "GET", null,null,false);
        }
       
     

    </script>
    </div>
    
</body>