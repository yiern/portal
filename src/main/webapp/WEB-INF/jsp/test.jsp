<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/graph.css">
    <script type="text/javascript" src="js/sfn.js"></script>
</head>

<body>

</body>

</html>

<div id="graph-914" class="workflowgraph">
    <style>
        .graph-legend ul {
            list-style-type: none;
            padding: 10px;
            padding-left: 0;
            margin: 0;
            position: absolute;
            top: 0;
            background: transparent;
        }

        \n",


        .graph-legend li {
            margin-left: 10px;
            display: inline-block;
        }

        .graph-legend li>div {
            width: 10px;
            height: 10px;
            display: inline-block;
        }

        \n",


        .graph-legend .success {
            background-color: #2BD62E
        }

        .graph-legend .failed {
            background-color: #DE322F
        }

        .graph-legend .cancelled {
            background-color: #DDDDDD
        }

        .graph-legend .in-progress {
            background-color: #53C9ED
        }

        .graph-legend .caught-error {
            background-color: #FFA500
        }

    </style>
    <div class="graph-legend">
        <ul>
            <li>
                <div class="success"></div>
                <span>Success</span>\
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
        </ul>
    </div>

    <svg></svg>
    <a href="https://console.aws.amazon.com/states/home?region=ap-southeast-1#/executions/details/arn:aws:states:ap-southeast-1:764277912183:execution:MyWorkflow_Simple:75c6bcf9-ecdb-42d4-882e-f94f3afa0a00" target="_blank"> Inspect in AWS Step Functions </a>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        var element = document.getElementById('graph-914')
        var options = {
            width: parseFloat(getComputedStyle(element, null).width.replace("px", "/")),
            height: 1000,
            layout: 'LR',
            resizeHeight: true,
        };
        
        /* only once definition from console */
        var definition = {
            "Comment": "A Hello World example demonstrating various state types of the Amazon States Language",
            "StartAt": "Pass",
            "States": {
                "Pass": {
                    "Comment": "A Pass state passes its input to its output, without performing work. Pass states are useful when constructing and debugging state machines.",
                    "Type": "Pass",
                    "Next": "Yes"
                },
                "Yes": {
                    "Type": "Pass",
                    "Next": "Wait 60 sec"
                },
                "Wait 60 sec": {
                    "Comment": "A Wait state delays the state machine from continuing for a specified time.",
                    "Type": "Wait",
                    "Seconds": 60,
                    "Next": "Parallel State"
                },
                "Parallel State": {
                    "Comment": "A Parallel state can be used to create parallel branches of execution in your state machine.",
                    "Type": "Parallel",
                    "Next": "Hello World",
                    "Branches": [{
                            "StartAt": "Hello",
                            "States": {
                                "Hello": {
                                    "Type": "Pass",
                                    "End": true
                                }
                            }
                        },
                        {
                            "StartAt": "World",
                            "States": {
                                "World": {
                                    "Type": "Pass",
                                    "End": true
                                }
                            }
                        }
                    ]
                },
                "Hello World": {
                    "Type": "Pass",
                    "End": true
                }
            }
        };
        var elementId = '#graph-914';
        /* sample of recurring events from output from java program */
        var events = {
            "events":[{"timestamp":"Jun 23, 2020, 6:11:37 PM","type":"ExecutionStarted","id":1,"previousEventId":0,"executionStartedEventDetails":{"input":"{}","roleArn":"arn:aws:iam::764277912183:role/service-role/StepFunctions-HelloWorld-role-aef79990"}},{"timestamp":"Jun 23, 2020, 6:11:37 PM","type":"PassStateEntered","id":2,"previousEventId":0,"stateEnteredEventDetails":{"name":"Pass","input":"{}"}},{"timestamp":"Jun 23, 2020, 6:11:37 PM","type":"PassStateExited","id":3,"previousEventId":2,"stateExitedEventDetails":{"name":"Pass","output":"{}"}},{"timestamp":"Jun 23, 2020, 6:11:37 PM","type":"PassStateEntered","id":4,"previousEventId":3,"stateEnteredEventDetails":{"name":"Yes","input":"{}"}},{"timestamp":"Jun 23, 2020, 6:11:37 PM","type":"PassStateExited","id":5,"previousEventId":4,"stateExitedEventDetails":{"name":"Yes","output":"{}"}},{"timestamp":"Jun 23, 2020, 6:11:37 PM","type":"WaitStateEntered","id":6,"previousEventId":5,"stateEnteredEventDetails":{"name":"Wait 60 sec","input":"{}"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"WaitStateExited","id":7,"previousEventId":6,"stateExitedEventDetails":{"name":"Wait 60 sec","output":"{}"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"ParallelStateEntered","id":8,"previousEventId":7,"stateEnteredEventDetails":{"name":"Parallel State","input":"{}"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"ParallelStateStarted","id":9,"previousEventId":8},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"PassStateEntered","id":10,"previousEventId":9,"stateEnteredEventDetails":{"name":"Hello","input":"{}"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"PassStateExited","id":11,"previousEventId":10,"stateExitedEventDetails":{"name":"Hello","output":"{}"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"PassStateEntered","id":12,"previousEventId":9,"stateEnteredEventDetails":{"name":"World","input":"{}"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"PassStateExited","id":13,"previousEventId":12,"stateExitedEventDetails":{"name":"World","output":"{}"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"ParallelStateSucceeded","id":14,"previousEventId":13},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"ParallelStateExited","id":15,"previousEventId":13,"stateExitedEventDetails":{"name":"Parallel State","output":"[{},{}]"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"PassStateEntered","id":16,"previousEventId":15,"stateEnteredEventDetails":{"name":"Hello World","input":"[{},{}]"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"PassStateExited","id":17,"previousEventId":16,"stateExitedEventDetails":{"name":"Hello World","output":"[{},{}]"}},{"timestamp":"Jun 23, 2020, 6:12:37 PM","type":"ExecutionSucceeded","id":18,"previousEventId":17,"executionSucceededEventDetails":{"output":"[{},{}]"}}],
        };


        var graph = new sfn.StateMachineExecutionGraph(definition, events, elementId, options);
        graph.render();
    });

</script>
