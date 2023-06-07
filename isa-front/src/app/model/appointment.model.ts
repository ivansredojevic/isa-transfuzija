export class AppointmentModel {

    id: number;
    username: string;
    center: string;
    centerId: number;
    modifiedTime: string;
    startTime: string;
    duration: number;
    priceEuro: number;
    taken: boolean;
    approved: boolean;
    doctors: string;
    doctorIds: number[];

}