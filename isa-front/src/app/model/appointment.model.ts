import { ApplicationUserModel } from "./applicationUser.model";
import { CenterModel } from "./center.model";

export class AppointmentModel {

    id: number;
    center: CenterModel;
    applicationUser: ApplicationUserModel;
    modifiedTime: string;
    startTime: string;
    duration: number;
    priceEuro: number;
    taken: boolean;
    approved: boolean;

}