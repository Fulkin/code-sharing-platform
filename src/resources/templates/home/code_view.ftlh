<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
          href="/css/style.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<span id="load_date">${code.date}</span><br>
<#if code.viewsRestrictions == true>
    <p><span id="views_restriction">${code.views} more views allowed</span></p>
</#if>
<#if code.timeRestrictions == true>
    <p>The code will be available for <span id="time_restriction">${code.time}</span> seconds</p>
</#if>

<pre id="code_snippet">
        <code>${codeSnippet}</code>
</pre>

</body>
</html>