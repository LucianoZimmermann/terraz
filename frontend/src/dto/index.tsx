import { FactorTypeEnum } from "../constant/enum";

export interface PriceFactorDTO {
  id?: number;
  factor: number;
}

export interface NeighborhoodDTO {
  id?: number;
  name: string;
  priceFactor: PriceFactorDTO;
}

export interface AddressDTO {
  id?: number;
  street: string;
  number: string;
  city: string;
  state: string;
  cep: string;
  neighborhood: NeighborhoodDTO;
}

export interface FactorDTO {
  id?: number;
  quoteId: number;
  thirdPartyId: number;
  materialCost: number;
  laborCost: number;
  factorTypeId: number;
}

export interface FactorTypeDTO {
  id: number;
  factorTypeEnum: FactorTypeEnum;
}

export interface ThirdPartyDTO {
  id: number;
  name: string;
  cnpj: string;
  factorType: FactorTypeDTO;
}

export interface TractOwnerDTO {
  id?: number;
  name: string;
  cpf: string;
}

export interface TractDTO {
  id?: number;
  squareMeters: number;
  address: AddressDTO;
  tractOwner: TractOwnerDTO;
}
