export interface PriceFactorDTO {
  id?: number;
  factor: number;
}

export interface AddressDTO {
  id?: number;
  street: string;
  number: string;
  neighborhood: string;
  city: string;
  state: string;
  cep: string;
  priceFactorId: number;
}

export interface TractDTO {}
