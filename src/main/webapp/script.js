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
            name: "Profile & Langages",
            template: "step2.html"
          },   
          {
            step: 3,
            name: "Third step",
            template: "step3.html"
          },             
        ];
        vm.user = {};
        
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
            
            
            "Email: " + vm.user.email + "\n" + 
            "Age: " + vm.user.age);
        }
    }
    
})();