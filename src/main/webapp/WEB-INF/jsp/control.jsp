<!DOCTYPE html>
<head>
   <script src = "codemirror/lib/codemirror.js"></script>
   <script src = "codemirror/mode/javascript/javascript.js"></script>
   <link href = "codemirror/lib/codemirror.css" rel="stylesheet"/>
   <link href="codemirror/theme/darcula.css" rel = "stylesheet"/>
   <link href="codemirror/theme/base16-dark.css" rel = "stylesheet"/>
   <script src = "js/jquery.js"></script>
</head>
<body>
    <textarea id = "editor" onkeyup="SaveCode()"></textarea>

    <form method="POST" action="control.jsp">
    <button id = "Save" onclick="SaveCode()">Save to file</button>
    </form>

    <script>
        var editor = 
            CodeMirror.fromTextArea(document.getElementById("editor"),{
            mode: { name: "javascript", json: true },
            theme: "base16-dark",
            lineNumbers: true,
            smartIndent: true,
            extraKeys: {"Ctrl-Space": "autocomplete"}
        });
        var textarea = document.getElementById("editor");

        editor.on("change",function(){
            var foo = editor.getValue();
            console.log(foo);

            $.ajax({
                    type: "POST",
                    contentType : 'application/json; charset=utf-8',
                    dataType : 'json',
                    url: "/awsget",
                    data: JSON.stringify(foo), // Note it is important
                    success :function(result) {
                    // do what ever you want with data
                    }
                });
           })


            

        function SaveCode(){        
           
           
        }
        
    </script>
    
</body>