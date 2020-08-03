import {Injectable, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ModalWindowService {

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService,) { }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  closeModal(){
    this.modalRef.hide()
  }
}
