import { Component, OnInit } from '@angular/core';
import { SearchService } from '../search/search.service';
import { FormControl } from '@angular/forms';
//import { map } from 'rxjs/operators';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-search',
  template: `
  <section class="filter-wrapper">
  <div class="keyword-wrapper">
    <input [formControl]="queryField" type="text" id="keyword" placeholder="pesquisando por correntistas..." autofocus/>
  </div>
  <ul class="filter-select">
    <li *ngFor="let result of results" class="filter-select-list">
      <p class="correntista-nome">
        {{result.name}}
      </p>

      <span class="tags" *ngFor='let genre of result?.genres  | slice:0:6'>{{genre}}</span>
  </ul>
</section>
  
  `
})
export class SearchComponent implements OnInit {
  results: any[] = [];
  queryField: FormControl = new FormControl();
  constructor(private _searchService: SearchService) { }
  ngOnInit() {

    this.queryField.valueChanges
      .subscribe(queryField => this._searchService.search(queryField)
        .subscribe(result => console.log(result)));
  }

}