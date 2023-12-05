import {Product} from "./product";

export class Cart{

  id:number;
  itemList:Product[]=[];

  constructor(id:number, itemList:Product[]){
    this.id = id;
    this.itemList = itemList;
  }

  get getCartId():number{
    return this.id;
  }

  get items(): Product[] {
    return this.itemList;
  }

  set items(value: Product[]) {
    this.itemList = value;
  }

}
