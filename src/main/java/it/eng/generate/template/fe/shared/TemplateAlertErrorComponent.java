package it.eng.generate.template.fe.shared;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAlertErrorComponent extends AbstractResourceTemplate {

	public TemplateAlertErrorComponent(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { Component, OnDestroy } from '@angular/core';\r\n" +
		"import { TranslateService } from '@ngx-translate/core';\r\n" +
		"import { JhiEventManager, JhiAlertService } from 'ng-jhipster';\r\n" +
		"import { Subscription } from 'rxjs';\r\n" +
		"@Component({\r\n" +
		"    selector: 'jhi-alert-error',\r\n" +
		"    template: `\r\n" +
		"        <div class=\"alerts\" role=\"alert\">\r\n" +
		"            <div *ngFor=\"let alert of alerts\" [ngClass]=\"setClasses(alert)\">\r\n" +
		"                <ngb-alert *ngIf=\"alert && alert.type && alert.msg\" [type]=\"alert.type\" (close)=\"alert.close(alerts)\">\r\n" +
		"                    <pre [innerHTML]=\"alert.msg\"></pre>\r\n" +
		"                </ngb-alert>\r\n" +
		"            </div>\r\n" +
		"        </div>`\r\n" +
		"})\r\n" +
		"export class JhiAlertErrorComponent implements OnDestroy {\r\n" +
		"    alerts: any[];\r\n" +
		"    cleanHttpErrorListener: Subscription;\r\n" +
		"    /* tslint:disable */\r\n" +
		"    constructor(private alertService: JhiAlertService, private eventManager: JhiEventManager, private translateService: TranslateService) {\r\n" +
		"        /* tslint:enable */\r\n" +
		"        this.alerts = [];\r\n" +
		"        this.cleanHttpErrorListener = eventManager.subscribe('"+conf.getProjectName()+"App.httpError', response => {\r\n" +
		"            let i;\r\n" +
		"            const httpErrorResponse = response.content;\r\n" +
		"            switch (httpErrorResponse.status) {\r\n" +
		"                // connection refused, server not reachable\r\n" +
		"                case 0:\r\n" +
		"                    this.addErrorAlert('Server not reachable', 'error.server.not.reachable');\r\n" +
		"                    break;\r\n" +
		"                case 400:\r\n" +
		"                    const arr = httpErrorResponse.headers.keys();\r\n" +
		"                    let errorHeader = null;\r\n" +
		"                    let entityKey = null;\r\n" +
		"                    arr.forEach(entry => {\r\n" +
		"                        if (entry.toLowerCase().endsWith('app-error')) {\r\n" +
		"                            errorHeader = httpErrorResponse.headers.get(entry);\r\n" +
		"                        } else if (entry.toLowerCase().endsWith('app-params')) {\r\n" +
		"                            entityKey = httpErrorResponse.headers.get(entry);\r\n" +
		"                        }\r\n" +
		"                    });\r\n" +
		"                    if (errorHeader) {\r\n" +
		"                        const entityName = translateService.instant('global.menu.entities.' + entityKey);\r\n" +
		"                        this.addErrorAlert(errorHeader, errorHeader, { entityName });\r\n" +
		"                    } else if (httpErrorResponse.error !== '' && httpErrorResponse.error.fieldErrors) {\r\n" +
		"                        const fieldErrors = httpErrorResponse.error.fieldErrors;\r\n" +
		"                        for (i = 0; i < fieldErrors.length; i++) {\r\n" +
		"                            const fieldError = fieldErrors[i];\r\n" +
		"                            if (['Min', 'Max', 'DecimalMin', 'DecimalMax'].includes(fieldError.message)) {\r\n" +
		"                                fieldError.message = 'Size';\r\n" +
		"                            }\r\n" +
		"                            // convert 'something[14].other[4].id' to 'something[].other[].id' so translations can be written to it\r\n" +
		"                            const convertedField = fieldError.field.replace(/\\[\\d*\\]/g, '[]');\r\n" +
		"                            const fieldName = translateService.instant('"+conf.getProjectName()+"App.' + fieldError.objectName + '.' + convertedField);\r\n" +
		"                            this.addErrorAlert('Error on field \"' + fieldName + '\"', 'error.' + fieldError.message, { fieldName });\r\n" +
		"                        }\r\n" +
		"                    } else if (httpErrorResponse.error !== '' && httpErrorResponse.error.message) {\r\n" +
		"                        this.addErrorAlert(\r\n" +
		"                            httpErrorResponse.error.message,\r\n" +
		"                            httpErrorResponse.error.message,\r\n" +
		"                            httpErrorResponse.error.params\r\n" +
		"                        );\r\n" +
		"                    } else {\r\n" +
		"                        this.addErrorAlert(httpErrorResponse.error);\r\n" +
		"                    }\r\n" +
		"                    break;\r\n" +
		"                case 404:\r\n" +
		"                    this.addErrorAlert('Not found', 'error.url.not.found');\r\n" +
		"                    break;\r\n" +
		"                default:\r\n" +
		"                    if (httpErrorResponse.error !== '' && httpErrorResponse.error.message) {\r\n" +
		"                        this.addErrorAlert(httpErrorResponse.error.message);\r\n" +
		"                    } else {\r\n" +
		"                        this.addErrorAlert(httpErrorResponse.error);\r\n" +
		"                    }\r\n" +
		"            }\r\n" +
		"        });\r\n" +
		"    }\r\n" +
		"    setClasses(alert) {\r\n" +
		"        return {\r\n" +
		"            toast: !!alert.toast,\r\n" +
		"            [alert.position]: true\r\n" +
		"        };\r\n" +
		"    }\r\n" +
		"    ngOnDestroy() {\r\n" +
		"        if (this.cleanHttpErrorListener !== undefined && this.cleanHttpErrorListener !== null) {\r\n" +
		"            this.eventManager.destroy(this.cleanHttpErrorListener);\r\n" +
		"            this.alerts = [];\r\n" +
		"        }\r\n" +
		"    }\r\n" +
		"    addErrorAlert(message, key?, data?) {\r\n" +
		"        key = key && key !== null ? key : message;\r\n" +
		"        this.alerts.push(\r\n" +
		"            this.alertService.addAlert(\r\n" +
		"                {\r\n" +
		"                    type: 'danger',\r\n" +
		"                    msg: key,\r\n" +
		"                    params: data,\r\n" +
		"                    timeout: 5000,\r\n" +
		"                    toast: this.alertService.isToast(),\r\n" +
		"                    scoped: true\r\n" +
		"                },\r\n" +
		"                this.alerts\r\n" +
		"            )\r\n" +
		"        );\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "alert-error.component";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/shared/alert";
	}

}
