(function () {

    angular.module('app', []);
    angular.module('app').controller("WizardController", [wizardController]);

    
    function wizardController() {
        var vm = this;
        
        //Model
        vm.currentStep = 1;
        vm.steps = [
          {
            step: 1,
            name: "Configurazione",
            template: "step1.html"
          },
          {
            step: 2,
            name: "Profili e Lingue",
            template: "step2.html"
          },   
          {
            step: 3,
            name: "Entita'",
            template: "step3.html"
          },             
          {
        	  step: 4,
        	  name: "Relazioni",
        	  template: "step4.html"
          },             
        ];
        
        //Languages
        vm.currentlang = '';
        vm.languages = ["it"];
        
    	vm.addLanguage = function() {
    		if(!!vm.currentlang && vm.languages.indexOf(vm.currentlang) == -1){
    			vm.languages.push(vm.currentlang);
    			vm.currentlang = '';
    		}
        }

        vm.removeLanguage = function(item) {
        	while (!!item && vm.languages.indexOf(item) !== -1) {
        		  delete vm.languages[vm.languages.indexOf(item)];
        		  //document.getElementById('language_'+item).remove();
        		  //document.getElementById('language_').remove();
    		}
        }
        
        
        //Profiles
        vm.currentprofile = '';
        vm.profiles = ["ROLE_ADMIN", "ROLE_USER"];
        vm.addProfile = function() {
        	if(!!vm.currentprofile){
        		vm.profiles.push(vm.currentprofile);
        		vm.currentprofile = '';
        	}
        }
        
        
        
        //INIZIALIZATION SMART
        //vm.user = {};
        vm.user = 
        {
        	  "generateTest":true,
        	  "owner":null,
        	  "tablePartName":"",
        	  "app" : "App",
        	  "enableReverseEngineeringDB" : false,
        	  "driver" : "com.mysql.jdbc.Driver",
        	  "dataBaseName" : "angulardb",
        	  "packageclass" : "it.exprivia",
        	  "username" : "root",
        	  "password" : "1980Mysql!",
        	  "pathname" : "C:\\eclipse-workspace\\",
        	  "pathnameEnvB" : "/Users/marco/eclipse-workspace/",
        	  "projectName" : "demogenerated",
        	  "urlConnection" : "jdbc:mysql://127.0.0.1:3306/angulardb?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=true&serverTimezone=UTC",
        	  "languages" : vm.languages,
        	  "profiles" : vm.profiles,
        	  "enumerations" : [ {"name":"TipoCliente", "values":"CLIENTE#ACQUIRENTE"},
        						 {"name":"BooleanStatus", "values":"SI#NO"},
        						 {"name":"SessoType", "values":"MALE#FEMALE"},
        						 {"name":"EsitoStatus", "values":"OK#KO"},
        						 {"name":"CanaleTrasmissione", "values":"MAIL#SMS#FACEBOOK#TWITTER#INSTAGRAM"},
        						 {"name":"TipoTrattativa", "values":"COMPRAVENDITA#LOCAZIONE"}
        					 ],	 
        	  "entities" : [ 
        	  	{"name":"Autore", "fields": [
        	  			{"fname":"id", "ftype":"Long", "frequired":true, "fsize":45}, 
        	  			{"fname":"nome", "ftype":"String", "frequired":true, "fsize":150}, 
        	  			{"fname":"cognome", "ftype":"String", "frequired":false, "fsize":150}, 
        	  			{"fname":"sesso", "ftype":"SessoType", "frequired":false, "fsize":10}, 
        	  			{"fname":"datanascita", "ftype":"LocalDate", "frequired":false, "fsize":null}, 
        	  			{"fname":"note", "ftype":"String", "frequired":false, "fsize":1000}
        	  	], "profiles" : [ "ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS", "ROLE_OPERATOR" ]},
        	  	{"name":"Libro", "fields": [
        	  			{"fname":"id", "ftype":"Long", "frequired":true, "fsize":45}, 
        	  			{"fname":"isbn", "ftype":"String", "frequired":true, "fsize":150}, 
        	  			{"fname":"name", "ftype":"String", "frequired":true, "fsize":500}, 
        	  			{"fname":"publishyear", "ftype":"String", "frequired":false, "fsize":4}, 
        	  			{"fname":"copies", "ftype":"Integer", "frequired":false, "fsize":21}
        	  	],"profiles" : [ "ROLE_ADMIN" ]},
        	  	{"name":"Collana", "fields": [
        	  			{"fname":"id", "ftype":"Long", "frequired":true, "fsize":45}, 
        	  			{"fname":"nome", "ftype":"String", "frequired":true, "fsize":500}
        	  	], "profiles" : [ "ROLE_OPERATOR", "ROLE_USER" ]},
        	  	{"name":"Cliente", "fields": [
        	  			{"fname":"id", "ftype":"Long", "frequired":true, "fsize":45}, 
        	  			{"fname":"firstname", "ftype":"String", "frequired":true, "fsize":500}, 
        	  			{"fname":"lastname", "ftype":"String", "frequired":true, "fsize":500}, 
        	  			{"fname":"type", "ftype":"TipoCliente", "frequired":false, "fsize":500}, 
        	  			{"fname":"email", "ftype":"String", "frequired":false, "fsize":500}, 
        	  			{"fname":"address", "ftype":"String", "frequired":false, "fsize":600}
        	  	],"profiles" : [ "ROLE_USER" ]},
        	  	{"name":"Azienda", "fields": [
        	  			{"fname":"id", "ftype":"Long", "frequired":true, "fsize":45}, 
        	  			{"fname":"codice", "ftype":"String", "frequired":true, "fsize":10, "fminlength": 3, "fmaxlength": 10},
        	  			{"fname":"nominativo", "ftype":"String", "frequired":true, "fsize":1000},
        	  			{"fname":"mail", "ftype":"String", "frequired":true, "fsize":1000, "fpattern": "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"},
        	  			{"fname":"canale", "ftype":"CanaleTrasmissione", "frequired":true, "fsize":500}
        	  	]},
        	  	{"name":"Settore", "fields": [
        	  			{"fname":"id", "ftype":"Long", "frequired":true, "fsize":45}, 
        	  			{"fname":"code", "ftype":"String", "frequired":true, "fsize":100},
        	  			{"fname":"name", "ftype":"String", "frequired":true, "fsize":100}
        		]}
        	  ],
        	 "relations" : [
        		{"type":"OneToOne", "sxTable":"Autore", "sxName":"bestseller", "sxSelect":"isbn" , "dxTable":"Libro", "dxName":"writer", "dxSelect":"cognome"},
        		{"type":"ManyToMany", "sxTable":"Azienda", "sxName":"mysector", "sxSelect":"code" , "dxTable":"Settore", "dxName":"myazienda", "dxSelect":"nominativo"},
        		{"type":"ManyToMany", "sxTable":"Azienda", "sxName":"mysector2", "sxSelect":"code" , "dxTable":"Settore", "dxName":"myazienda2", "dxSelect":"nominativo"},
        		{"type":"OneToMany", "sxTable":"Autore", "sxName":"clienti", "sxSelect":"firstname" , "dxTable":"Cliente", "dxName":"preferito", "dxSelect":"nome"},
        		{"type":"OneToMany", "sxTable":"Autore", "sxName":"clienti2", "sxSelect":"firstname" , "dxTable":"Cliente", "dxName":"preferito2", "dxSelect":"nome"},
        		{"type":"ManyToOne", "sxTable":"Libro", "sxName":"collana", "sxSelect":"nome" , "dxName":"have", "dxTable":"Collana"},
        		{"type":"ManyToOne", "sxTable":"Libro", "sxName":"collana2", "sxSelect":"nome" , "dxName":"have2", "dxTable":"Collana"}
        	  ]  
        	};


        
        
        //Functions
        vm.gotoStep = function(newStep) {
          vm.currentStep = newStep;
        }
        
        vm.getStepTemplate = function(){
          for (var i = 0; i < vm.steps.length; i++) {
                if (vm.currentStep == vm.steps[i].step) {
                    return vm.steps[i].template;
                }
            }
        }
        
        vm.save = function() {
          alert(
            "Saving form... \n\n" + 

            //Step 1
            "generateTest: " + vm.user.generateTest + "\n" + 
            "owner: " + vm.user.owner + "\n" + 
            "tablePartName: " + vm.user.tablePartName + "\n" + 
            "app: " + vm.user.app + "\n" + 
            "enableReverseEngineeringDB: " + vm.user.enableReverseEngineeringDB + "\n" + 
            "driver: " + vm.user.driver + "\n" + 
            "dataBaseName: " + vm.user.dataBaseName + "\n" + 
            "packageclass: " + vm.user.packageclass + "\n" + 
            "password: " + vm.user.password + "\n" + 
            "pathname: " + vm.user.pathname + "\n" + 
            "pathnameEnvB: " + vm.user.pathnameEnvB + "\n" + 
            "projectName: " + vm.user.projectName + "\n" + 
            "urlConnection: " + vm.user.urlConnection + "\n" + 
            
            //Step 2
            "languages: " + vm.user.languages + "\n" + 
            "profiles: " + vm.user.profiles + "\n" + 
            "enumerations: " + vm.user.enumerations + "\n" + 

            //Step 3
            "entities: " + vm.user.entities + "\n" + 

            //Step 4
            "relations: " + vm.user.relations);
        }
    }
    
})();