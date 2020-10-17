<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <script src="/js/jquery.js"></script>
    <script src = "/js/codemirror.js"></script>
    <script src = "/js/javascript.js"></script>
    <script src="/js/sfn.js"></script>
   
    <link rel="stylesheet" type = "text/css" href = "/css/elegant.css">
    <link rel="stylesheet" type = "text/css" href = "/css/darcula.css">
    <link rel="stylesheet" type="text/css" href="/css/graph.css">
    <link rel="stylesheet" type="text/css" href="/css/codemirror.css">
    
</head>

<body>
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
                var element = document.getElementById('graph-914')
                var options = 
                        {
                            width: parseFloat(getComputedStyle(element, null).width.replace("px", "/")),
                            height: 1000,
                            layout: 'LR',
                            resizeHeight: true,
                        };
                var userDefinition = '${definition}';
                var definition = JSON.parse(userDefinition);
                var event = '${event}';
                var eventss = JSON.stringify(event)
                
                var elementId = '#graph-914';
                /* sample of recurring events from output from java program */
                var events = {
                    "events": eventss };

                var graph = new sfn.StateMachineExecutionGraph(definition, events, elementId, options);
                graph.render();
            </script>
</body>