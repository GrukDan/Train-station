import {Component, Input, OnInit} from '@angular/core';
import {Country} from "../../models/station-details/country";
import {TreeNode} from "angular-tree-component/dist/defs/api";
import {async} from "@angular/core/testing";

@Component({
  selector: 'app-tree-station',
  templateUrl: './tree-station.component.html',
  styleUrls: ['./tree-station.component.css']
})
export class TreeStationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @Input()
  header:string = 'Header';

  @Input()
  nodes:any[];

  options = {
    useVirtualScroll: true,
    animateExpand: true,
    animateSpeed: 30,
  };

}
