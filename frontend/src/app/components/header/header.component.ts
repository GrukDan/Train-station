import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {Subscription} from "rxjs";
import {RoleService} from "../../services/user-details/role.service";
import {Role} from "../../models/user-details/role";
import {User} from "../../models/user-details/user";
import {UserService} from "../../services/user-details/user.service";
import {ModalWindowService} from "../../services/modal-window/modal-window.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User;
  userRole: number;
  roles: Role[];
  private subscriptions: Subscription[];

  constructor(private roleService: RoleService,
              private userService: UserService,
              public modalWindowService: ModalWindowService) {
    this.subscriptions = [];
    this.createVoidUserForm();
  }

  ngOnInit() {
  }

  openCreateUserModal(template: TemplateRef<any>) {
    this.modalWindowService.openModal(template);
    this.loadRoles();
  }

  loadRoles() {
    this.subscriptions.push(this.roleService.getRoles().subscribe(roles => {
      this.roles = roles as Role[];
    }))
  }

  addUser(user: User, idRole: number) {
    user.roles.push(this.roles.find(role => role.idRole == idRole));
    console.log(user)
    this.subscriptions.push(this.userService.save(user).subscribe())
    this.createVoidUserForm();
  }

  createVoidUserForm(){
    this.user = new User();
    this.roles = [];
    this.userRole = null;
  }
}
