import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Injectable({
    providedIn: 'root'
})

export class SnackService {

    constructor(public snackBar: MatSnackBar) { }

    showSnack(snackMessage: string, action: string) {
        const config = new MatSnackBarConfig();
        config.verticalPosition = 'top';
        config.duration = 5000;
        this.snackBar.open(snackMessage, action, config);
    }

}