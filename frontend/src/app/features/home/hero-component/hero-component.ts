import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import 'iconify-icon';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';


@Component({
  selector: 'app-hero-component',
  standalone: true,
  imports: [CommonModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './hero-component.html'
})
export class HeroComponent { }
