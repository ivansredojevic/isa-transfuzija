export class ApplicationUser {
    id: number;
    username: string="";
    password: string="";
    email: string="";
    name: string="";
    surname: string="";
    address: string;
    phone: string;
    jmbg: string;
    sex: string;
    occupation: string;
    jobinformation: string;
    activated: boolean = false;
    role: string;
    token: string;
    penalty: number;
  }
  