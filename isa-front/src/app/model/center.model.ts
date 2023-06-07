export class CenterModel {

    public selected = false;
    
    constructor(
        public id: number,
        public centerName: string,
        public address: string,
        public rating: number,
        public openTime: string,
        public closedTime: string) {}

	// constructor(object?: any) {
    //     if(object) {
    //         this.id = object.id;
    //         this.centerName = object.centerName;
    //         this.address = object.address;
    //         this.rating = object.rating;
    //         this.openTime = object.openTime;
    //         this.closedTime = object.closedTime;
    //     }
	// }
}
  