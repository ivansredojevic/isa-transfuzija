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
    complainPers: boolean;
    complainCenter: boolean;
    doctors: string;
    doctorIds: number[];
    canReserve: boolean;
    canCancel: boolean;

}