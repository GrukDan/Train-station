import {Component, Input, OnInit} from '@angular/core';
import { TreeNode, TreeModel, TREE_ACTIONS, KEYS, IActionMapping, ITreeOptions } from 'angular-tree-component';

const actionMapping: IActionMapping = {
  mouse: {
    click: (tree, node, $event) => {
    }
  }
};



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

  options: ITreeOptions = {
    actionMapping
  };
}
