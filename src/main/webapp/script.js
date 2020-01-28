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
            name: "Linguaggio e Profilazione",
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
	    		if(!!vm.currentlang && vm.languages.indexOf(vm.currentlang) == -1) {
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
        vm.currentProfile = '';
        vm.profiles = ["ROLE_ADMIN", "ROLE_USER"];
        vm.addProfile = function() {
        		if(!!vm.currentProfile) {
        			vm.profiles.push(vm.currentProfile);
        			vm.currentProfile = '';
        		}
        }
        
        
        //Enumerations
        vm.currentEnumName = '';
        vm.currentEnumValue = '';
        vm.enumerations = [];
        vm.addEnumeration = function() {
	        	if(!!vm.currentEnumName && !!vm.currentEnumValue) {
	        		var cEnum = {"name" : vm.currentEnumName, "values" : vm.currentEnumValue };
	        		vm.enumerations.push(cEnum);
	        		vm.currentEnumName = '';
	        		vm.currentEnumValue = '';
	        	}
        }
        
        
        //Entities
        vm.currentEntityName = '';
        vm.currentEntityFields = [];
        vm.currentFieldName = '';
        vm.currentFieldType = '';
        vm.currentFieldRequired = '';
        vm.currentFieldSize = '';
        vm.entityProfiles = [];
        vm.currentEntities = [];

        
        
        vm.entityProfile = '';
        vm.entityProfiles = vm.profiles;
        vm.addEntityProfile = function() {
        		if(!!vm.entityProfile) {
        			vm.entityProfiles.push(vm.entityProfile);
        			vm.entityProfile = '';
        		}
        }
        
        vm.addField = function() {
	        	if(!!vm.currentFieldName && !!vm.currentFieldType) {
	        		var cField = { "fname": vm.currentFieldName, 
	        					   "ftype": vm.currentFieldType, 
	        					   "frequired": vm.currentFieldRequired, 
	        					   "fsize": vm.currentFieldSize };
	        		vm.currentEntityFields.push(cField);
	        		vm.currentFieldName = '';
	        	    vm.currentFieldType = '';
	        	    vm.currentFieldRequired = '';
	        	    vm.currentFieldSize = '';
	        	}
	    }
        
        vm.addEntity = function() {
        	if(!!vm.currentEntityName && !!vm.currentEntityFields) {
        		var cEntity = { "name": vm.currentEntityName, 
        						"fields": vm.currentEntityFields, 
        						"profiles": vm.entityProfiles };
        		vm.currentEntities.push(cEntity);
        		vm.currentEntityName = '';
        		vm.currentEntityFields = [];
        		//vm.entityProfiles = [];  //FIXME
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
        	  "enumerations" : vm.enumerations,
        	  "entities" : vm.currentEntities,
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
        	var saved =  
        	"Saving form... \n\n" + 
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
	            "languages: " + vm.user.languages + "\n" + 
	            "profiles: " + vm.user.profiles + "\n" + 
	            "enumerations: " + JSON.stringify(vm.user.enumerations) + "\n" + 
	            "entities: " + JSON.stringify(vm.user.entities) + "\n" + 
	            "relations: " + JSON.stringify(vm.user.relations) ;
            console.log(saved);
        	alert(saved);
        }
    }
    
})();