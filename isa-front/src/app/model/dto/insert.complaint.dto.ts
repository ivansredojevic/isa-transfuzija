export class InsertComplaintDTO {

	constructor(

		public username: string,
		public complaintText: string,
		public appointmentId: number,
		public centerId: number,
		public personnelId: number) {}

}