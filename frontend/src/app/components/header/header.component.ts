import {Component, HostListener, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {Observable, of, Subscription} from "rxjs";
import {RoleService} from "../../services/user-details/role.service";
import {Role} from "../../models/user-details/role";
import {User} from "../../models/user-details/user";
import {UserService} from "../../services/user-details/user.service";
import {ModalWindowService} from "../../services/modal-window/modal-window.service";
import {Task} from "../../models/task-details/task";
import {Status} from "../../models/task-details/status";
import {TaskService} from "../../services/task-details/task.service";
import {StationService} from "../../services/station-details/station.service";
import {StatusService} from "../../services/task-details/status.service";
import {FormGroup} from "@angular/forms";
import {ValidationService} from "../../services/reactive-forms/validation.service";
import {TripService} from "../../services/station-details/trip.service";
import {TripRecord} from "../../models/view-models/trip-record";
import {Alternative} from "../../models/task-details/alternative";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit, OnDestroy {

  taskForm: FormGroup;
  userForm: FormGroup;

  user: User;
  task: Task;

  userRole: number;

  roles: Role[] = [];
  statuses: Status[] = [];
  tripRecords: TripRecord[] = [];

  experts: User[] = [];
  selectedExperts: number[] = [];
  expertsNumber: number = 0;

  alternatives: Alternative[] = [];
  selectedTrips: number[] = [];

  page: number = 0;

  private subscriptions: Subscription[] = [];

  constructor(private roleService: RoleService,
              private userService: UserService,
              private taskService: TaskService,
              private statusService: StatusService,
              private tripService: TripService,
              private validationService: ValidationService,
              public modalWindowService: ModalWindowService) {
  }

  ngOnInit() {
  }

  @HostListener('window:beforeunload')
  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  openUserModal(template: TemplateRef<any>) {
    this.createVoidUserForm();
    this.modalWindowService.openModal(template);
    this.loadRoles();
  }

  openTaskModal(template: TemplateRef<any>) {
    this.createVoidTaskForm();
    this.modalWindowService.openModal(template);
    this.loadStatuses();
    this.loadExperts();
    this.loadTrips();
  }

  loadRoles() {
    this.subscriptions.push(this.roleService.getRoles().subscribe(roles => {
      this.roles = roles as Role[];
    }))
  }

  loadStatuses() {
    this.subscriptions.push(this.statusService.getAll().subscribe(statuses => {
      this.statuses = statuses as Status[];
    }))
  }

  addUser(user: User, idRole: number) {
    user.roles.push(this.roles.find(role => role.idRole == idRole));
    this.subscriptions.push(this.userService.save(user).subscribe());
    this.userForm.reset();
    this.modalWindowService.closeModal();
  }

  addTask(task: Task) {
    this.subscriptions.push(
      this.selectExpertsById(this.collectSelectedIds(this.selectedExperts))
        .subscribe(selectedExperts => {
          task.users = selectedExperts;

          task.taskCreator = 16;
          task.dateOfCreation = new Date();
          this.subscriptions.push(this.taskService.save(task).subscribe());
          this.taskForm.reset();
          this.modalWindowService.closeModal();
        })
    );
  }

  createVoidUserForm() {
    this.userForm = this.validationService.getUserFormGroup();
    this.user = new User();
    this.roles = [];
    this.userRole = null;
  }

  createVoidTaskForm() {
    this.taskForm = this.validationService.getTaskFormGroup();
    this.taskForm.get('dateOfCreation').clearValidators();
    this.taskForm.get('taskCreator').clearValidators();
    this.task = new Task();
    this.statuses = [];
    this.tripRecords = [];
    this.experts = [];
  }

  get _userName() {
    return this.userForm.get('userName');
  }

  get _userSurname() {
    return this.userForm.get('userSurname');
  }

  get _email() {
    return this.userForm.get('email');
  }

  get _role() {
    return this.userForm.get('role');
  }

  get _taskName() {
    return this.taskForm.get('taskName');
  }

  get _description() {
    return this.taskForm.get('description');
  }

  get _status() {
    return this.taskForm.get('status');
  }

  loadTrips() {
    this.subscriptions.push(
      this.tripService
        .getTripRecordsLimitOrderedByCountry(this.page)
        .subscribe(tripRecords => {
          this.tripRecords = this.tripRecords.concat(tripRecords);
        }))
  }

  loadExperts() {
    this.subscriptions.push(
      this.userService
        .getExperts()
        .subscribe(experts => {
          this.experts = experts as User[];
          this.expertsNumber = this.experts.length;
          this.selectedExperts = (new Array<number>(this.expertsNumber)).fill(-1);
        }))
  }


  valueChanged(selected: number[], checked: boolean, value: any, index: number) {
    checked ? selected[index] = value : this.selectedExperts[index] = -1;
  }

  collectSelectedIds(selected: number[]): number[] {
    return selected.filter(id => id > 0);
  }

  selectExpertsById(ids: number[]): Observable<User[]> {
    return of(this.experts.filter(expert => ids.some(id => id == expert.idUser)));
  }

  loadMoreTrips() {
    this.page++;
    this.loadTrips();
  }
}
