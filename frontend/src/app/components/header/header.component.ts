import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {BsDropdownConfig} from "ngx-bootstrap/dropdown";
import {Subscription} from "rxjs";
import {RoleService} from "../../services/user-details/role.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  private subscriptions:Subscription[];

  constructor(private modalService: BsModalService,
              private roleService:RoleService) {
    this.subscriptions = [];
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
    this.loadRoles()
  }

  ngOnInit() {
  }


  loadRoles(){
    this.subscriptions.push(this.roleService.getRoles().subscribe(roles=>{
      console.log(roles)
    }))
  }


}
