<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet"
          href="/css/style.css">
</head>
<body>
    <form>
        <textarea id="code_snippet">// write your code here</textarea>
        <br>
        <span>Time restriction:</span>
        <input type="text" placeholder="0" id="time_restriction"><br>
        <span>Views restriction:</span>
        <input type="text" placeholder="0" id="views_restriction"><br>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </form>
    <script type="text/javascript" src="/js/sendScript.js"></script>
</body>
</html>