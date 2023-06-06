import { ApplicationUserModel } from "./applicationUser.model";

export class QuestionnaireModel {

    id: number;
    date: string;
    donationNumber: number;
    rejected: boolean;
    healthy: boolean;
    dangerousOccupation: boolean;
    infectious: boolean;
    bloodPressureIssues: boolean;
    onTherapy: boolean;
    aspirin: boolean;
    tatooed: boolean;
    applicationUser: ApplicationUserModel;

}