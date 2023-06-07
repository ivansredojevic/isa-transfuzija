import { ApplicationUserModel } from "./applicationUser.model";
import { CenterModel } from "./center.model";
import { PersonnelModel } from "./personnel.model";

export class AppointmentModel {

    id: number;
    center: string;
    modifiedTime: string;
    startTime: string;
    duration: number;
    priceEuro: number;
    taken: boolean;
    approved: boolean;
    doctors: string;

}