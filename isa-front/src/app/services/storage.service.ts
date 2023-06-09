import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  setItem(key: string, value: any) {
    sessionStorage.setItem(key, JSON.stringify(value));
    console.log('Added to storage: ' + key + ' : ' + value)
  }

  getItem(key: string) {
    return JSON.parse(sessionStorage.getItem(key) ?? '');
  }

  removeItem(key: string) {
    sessionStorage.removeItem(key);
  }
}