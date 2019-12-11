package it.eng.generate.template.fe.entities;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityDeleteComponentTs extends AbstractResourceTemplate {

	public TemplateEntityDeleteComponentTs(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String INometabella = Utils.getIName(tabella);


		String body = 
		"import { Component, OnInit, OnDestroy } from '@angular/core';\r\n" +
		"import { ActivatedRoute, Router } from '@angular/router';\r\n" +
		"import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';\r\n" +
		"import { JhiEventManager } from 'ng-jhipster';\r\n" +
		"import { "+INometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n" +
		"@Component({\r\n" +
		"    selector: 'jhi-"+nometabella+"-delete-dialog',\r\n" +
		"    templateUrl: './"+nometabella+"-delete-dialog.component.html'\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"DeleteDialogComponent {\r\n" +
		"    "+nometabella+": "+INometabella+";\r\n" +
		"    constructor(private "+nometabella+"Service: "+Nometabella+"Service, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}\r\n" +
		"    clear() {\r\n" +
		"        this.activeModal.dismiss('cancel');\r\n" +
		"    }\r\n" +
		"    confirmDelete(id: number) {\r\n" +
		"        this."+nometabella+"Service.delete(id).subscribe(response => {\r\n" +
		"            this.eventManager.broadcast({\r\n" +
		"                name: '"+nometabella+"ListModification',\r\n" +
		"                content: 'Deleted an "+nometabella+"'\r\n" +
		"            });\r\n" +
		"            this.activeModal.dismiss(true);\r\n" +
		"        });\r\n" +
		"    }\r\n" +
		"}\r\n" +
		"@Component({\r\n" +
		"    selector: 'jhi-"+nometabella+"-delete-popup',\r\n" +
		"    template: ''\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"DeletePopupComponent implements OnInit, OnDestroy {\r\n" +
		"    private ngbModalRef: NgbModalRef;\r\n" +
		"    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}\r\n" +
		"    ngOnInit() {\r\n" +
		"        this.activatedRoute.data.subscribe(({ "+nometabella+" }) => {\r\n" +
		"            setTimeout(() => {\r\n" +
		"                this.ngbModalRef = this.modalService.open("+Nometabella+"DeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });\r\n" +
		"                this.ngbModalRef.componentInstance."+nometabella+" = "+nometabella+";\r\n" +
		"                this.ngbModalRef.result.then(\r\n" +
		"                    result => {\r\n" +
		"                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });\r\n" +
		"                        this.ngbModalRef = null;\r\n" +
		"                    },\r\n" +
		"                    reason => {\r\n" +
		"                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });\r\n" +
		"                        this.ngbModalRef = null;\r\n" +
		"                    }\r\n" +
		"                );\r\n" +
		"            }, 0);\r\n" +
		"        });\r\n" +
		"    }\r\n" +
		"    ngOnDestroy() {\r\n" +
		"        this.ngbModalRef = null;\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-delete-dialog.component";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
