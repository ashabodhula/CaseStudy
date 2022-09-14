import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-getbooks',
  templateUrl: './getbooks.component.html',
  styleUrls: ['./getbooks.component.css']
})
export class GetbooksComponent implements OnInit {

  constructor(public readerService:ReaderService) { }

  ngOnInit(): void {
    this.readerService.getAllBooks();
  }

}
