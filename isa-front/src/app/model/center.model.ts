export class CenterModel {

    public selected = false;
    
    constructor(
        public id: number,
        public centerName: string,
        public address: string,
        public rating: number,
        public openTime: string,
        public closedTime: string) {}
        
}
  