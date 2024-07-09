# Guide to protect against xss atack.
Hi, if you are building a web application you probably know that they are very vulnerable. If you have more than 3 people on your site and a lot of active users who write posts or create articles, chances are there will be someone who wants to break everything and do an xss attack or sql injection.

**Cross-Site Scripting (XSS)** attacks are a type of security vulnerability typically found in web applications. These attacks occur when an attacker is able to inject malicious scripts into content from otherwise trusted websites. The injected code is then executed in the context of the user's browser, leading to various malicious outcomes such as data theft, session hijacking, and more.

For example, an xss attack that creates a warning.

`<script>alert('XSS!');</script>`

Usually these attacks are made in input fields, such as writing a post or article. In this small project you will find a quick and easy way to protect your small project from such attacks.

Today we're going to deal with [KefirBB](https://github.com/search?q=bbcode+java&type=repositories) . This is a library for java that provides BBcode syntax and methods of working with it. Actually BBcode provides also methods of working with markdawn and TxStyle, but unlike them BBcode is a syntax for working with forums and articles. BBcode is best used for working on such projects because of the syntax of writing to the database and subsequent escaping.

In fact, the BBcode syntax is very large and if you want and with the KefirBB library you can make a good forum with text spoiler and text editing, followed by output. But today we are considering bbcode as a tool to protect against such simple and nasty attacks as xss. 

# How to use [KefirBB](https://github.com/search?q=bbcode+java&type=repositories)

Here is the procedure for starting the coder itself.

**Start TextProcessor**

`@Bean
    public TextProcessor bbCodeProcessor() {
        return BBProcessorFactory.getInstance().create();
    }`
    
**Using the textProcessor.process method**

`String parsDescription = textProcessor.process(description);`

In fact, that's all, but it's also worth noting that this is a basic startup of TextProcessor, it can be initialized in a more non-standard way, 

`TextProcessor processor = BBProcessorFactory.getInstance().createFromResource(ConfigurationFactory.TEXTILE_CONFIGURATION_FILE);`

You can specify which of the three syntaxes you want. But as already mentioned for articles and forums it is best to use BBcode. 

# How textProcessor.process works

When you call textProcessor.process(description), the TextProcessor instance parses the BBCode string and converts it to HTML based on the rules defined in the configuration file (in our case, the standard rules).

The TextProcessor uses a parsing algorithm to scan through the input string and identify BBCode tags (e.g., [b], [/b], [url=...], [/url]).
For each BBCode tag, the processor looks up the corresponding HTML replacement in the configuration file.
The processor replaces BBCode tags with their corresponding HTML tags.
Attributes within BBCode tags (such as URLs) are also parsed and included in the HTML output.

In the basic KefirBB settings, there are many syntax elements missing, but you can write them yourself ([read the manual for more understanding](https://github.com/kefirfromperm/kefirbb/wiki/User-Guide-%28English%29)). 
