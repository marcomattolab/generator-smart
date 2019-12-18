package it.eng.generate.template.fe;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateIndex extends AbstractResourceTemplate {

	public TemplateIndex(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		// Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() 
		String body = "<!doctype html>\r\n" +
		"<html class=\"no-js\" lang=\"it\" dir=\"ltr\">\r\n" +
		"<head>\r\n" +
		"    <base href=\"./\" />\r\n" +
		"    <meta charset=\"utf-8\">\r\n" +
		"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" +
		"    <title>"+conf.getProjectName()+"</title>\r\n" +
		"    <meta name=\"description\" content=\"Description for "+conf.getProjectName()+"\">\r\n" +
		"    <meta name=\"google\" value=\"notranslate\">\r\n" +
		"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" +
		"    <meta name=\"theme-color\" content=\"#000000\">\r\n" +
		"    <link rel=\"shortcut icon\" href=\"favicon.ico\" />\r\n" +
		"    <link rel=\"manifest\" href=\"manifest.webapp\" />\r\n" +
		"    <link rel=\"stylesheet\" href=\"content/css/loading.css\">\r\n" +
		"    <!-- jhipster-needle-add-resources-to-root - JHipster will add new resources here -->\r\n" +
		"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n\n" +
		"</head>\r\n" +
		"<body>\r\n" +
		"    <!--[if lt IE 9]>\r\n" +
		"        <p class=\"browserupgrade\">You are using an <strong>outdated</strong> browser. Please <a href=\"http://browsehappy.com/\">upgrade your browser</a> to improve your experience.</p>\r\n" +
		"    <![endif]-->\r\n" +
		"    <jhi-main>\r\n" +
		"        <div class=\"app-loading\">\r\n" +
		"            <div class=\"lds-css ng-scope\">\r\n" +
		"                <div class=\"lds-pacman\">\r\n" +
		"                    <div><div></div><div></div><div></div></div>\r\n" +
		"                    <div><div></div><div></div><div></div></div>\r\n" +
		"                </div>\r\n" +
		"            </div>\r\n" +
		"        </div>\r\n" +
		"        <div class=\"app-loading\">\r\n" +
		"            <div id=\"jhipster-error\" style=\"display:none\">\r\n" +
		"                <!-- This content is for troubleshooting purpose and will be removed when app renders -->\r\n" +
		"                <h1>An error has occured :-(</h1>\r\n" +
		"                <h2>Usual error causes</h2>\r\n" +
		"                <ol>\r\n" +
		"                    <li>You started the application from an IDE and you didn't run <code style=\"color:red\">npm start</code> or <code style=\"color:red\">npm run webpack:build</code>.</li>\r\n" +
		"                    <li>You had a network error while running <code style=\"color:red\">npm install</code>. If you are behind a corporate proxy, it is likely that this error was caused by your proxy. Have a look at the JHipster error logs, you will probably have the cause of the error.</li>\r\n" +
		"                    <li>You installed a Node.js version that doesn't work with JHipster: please use an LTS (long-term support) version, as it's the only version we support.</li>\r\n" +
		"                </ol>\r\n" +
		"                <h2>Building the client side code again</h2>\r\n" +
		"                <p>If you want to go fast, run <code style=\"color:red\">./mvnw</code> to build and run everything.</p>\r\n" +
		"                <p>If you want to have more control, so you can debug your issue more easily, you should follow the following steps:</p>\r\n" +
		"                <ol>\r\n" +
		"                    <li>Install npm dependencies with the command <code style=\"color:red\">npm install</code></li>\r\n" +
		"                    <li>Build the client with the command <code style=\"color:red\">npm run webpack:build</code> or <code style=\"color:red\">npm start</code></li>\r\n" +
		"                    <li>Start the server with <code style=\"color:red\">./mvnw</code> or using your IDE</li>\r\n" +
		"                </ol>\r\n" +
		"                <h2>Getting more help</h2>\r\n" +
		"                <h3>If you have a question on how to use JHipster</h3>\r\n" +
		"                <p>\r\n" +
		"                    Go to Stack Overflow with the <a href=\"http://stackoverflow.com/tags/jhipster\" target=\"_blank\">\"jhipster\"</a> tag.\r\n" +
		"                </p>\r\n" +
		"                <h3>If you have a bug or a feature request</h3>\r\n" +
		"                <p>\r\n" +
		"                    First read our <a href=\"https://github.com/jhipster/generator-jhipster/blob/master/CONTRIBUTING.md\" target=\"_blank\">contributing guidelines</a>.\r\n" +
		"                </p>\r\n" +
		"                <p>\r\n" +
		"                    Then, fill a ticket on our <a href=\"https://github.com/jhipster/generator-jhipster/issues/new/choose\" target=\"_blank\">bug tracker</a>, we'll be happy to resolve your issue!\r\n" +
		"                </p>\r\n" +
		"                <h3>If you want to chat with contributors and other users</h3>\r\n" +
		"                <p>\r\n" +
		"                    Join our chat room on <a href=\"https://gitter.im/jhipster/generator-jhipster\" target=\"_blank\">Gitter.im</a>. Please note that this is a public chat room, and that we expect you to respect other people and write in a correct English language!\r\n" +
		"                </p>\r\n" +
		"                <!-- end of troubleshooting content -->\r\n" +
		"            </div>\r\n" +
		"        </div>\r\n" +
		"    </jhi-main>\r\n" +
		"    <noscript>\r\n" +
		"        <h1>You must enable javascript to view this page.</h1>\r\n" +
		"    </noscript>\r\n" +
		"    <script type=\"text/javascript\" language=\"javascript\">\r\n" +
		"        // show an error message if the app loading takes more than 5 sec\r\n" +
		"        window.onload=function() {\r\n" +
		"            setTimeout(showError, 4000);\r\n" +
		"        }\r\n" +
		"        function showError() {\r\n" +
		"            var errorElm = document.getElementById(\"jhipster-error\");\r\n" +
		"            if (errorElm && errorElm.style) {\r\n" +
		"                errorElm.style.display = \"block\";\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"    </script>\r\n" +
		"    <!-- uncomment this for adding service worker\r\n" +
		"        <script>\r\n" +
		"            if ('serviceWorker' in navigator) {\r\n" +
		"                window.addEventListener('load', function() {\r\n" +
		"                    navigator.serviceWorker.register('/service-worker.js')\r\n" +
		"                        .then(function () {\r\n" +
		"                            console.log('Service Worker Registered');\r\n" +
		"                        });\r\n" +
		"                });\r\n" +
		"            }\r\n" +
		"        </script>\r\n" +
		"    -->\r\n" +
		"    <!-- Google Analytics: uncomment and change UA-XXXXX-X to be your site's ID.\r\n" +
		"    <script>\r\n" +
		"        (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=\r\n" +
		"        function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;\r\n" +
		"        e=o.createElement(i);r=o.getElementsByTagName(i)[0];\r\n" +
		"        e.src='//www.google-analytics.com/analytics.js';\r\n" +
		"        r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));\r\n" +
		"        ga('create','UA-XXXXX-X');ga('send','pageview');\r\n" +
		"    </script>-->\r\n" +
		"</body>\r\n" +
		"</html>\r\n";
		return body;
	}

	public String getClassName(){
		return "index";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp";
	}

}
