
export interface Product {
  id:number
  producer: Producer
  category: Category
  name:string
  length:number
  width:number
  height:number
  price:number
  weight:number
  totalCount?:number
  description:string
  imageUrl1:string
  imageUrl2:string
  imageUrl3:string
  imageUrl4:string
  imageUrl5:string
  status:string
}

export interface Producer {
  id: number
  name: String
}

export interface Category {
  id: number
  name: String
}
