import { useState } from "react";
import { EntityType } from "../../constant/entity";
import { useEntity } from "../../context/EntityContext";
import AddressForm from "./Form/AddressForm";
import AddressList from "./List/Address/AddressList";
import EntityButtons from "./EntityButtons";
import Button from "../Button";
import PriceFactorList from "./List/PriceFactor";
import PriceFactorForm from "./Form/PriceFactorForm";

export default function Body() {
  const { entity } = useEntity();
  const [isCreating, setIsCreating] = useState(false);

  const handleAddClick = () => {
    setIsCreating(true);
  };

  const handleCancel = () => {
    setIsCreating(false);
  };

  const renderForm = () => {
    switch (entity) {
      case EntityType.ADDRESS:
        return <AddressForm onCancel={handleCancel} />;
      case EntityType.PRICE_FACTOR:
        return <PriceFactorForm onCancel={handleCancel} />;
      default:
        return <p>No form for this entity</p>;
    }
  };

  const renderList = () => {
    switch (entity) {
      case EntityType.ADDRESS:
        return <AddressList />;
      case EntityType.PRICE_FACTOR:
        return <PriceFactorList />;
      default:
        return <p>No data for this entity</p>;
    }
  };

  return (
    <div>
      <EntityButtons />
      <Button content={`Adicionar ${entity}`} onClick={handleAddClick} />
      <Button content={`Listar ${entity}`} onClick={handleCancel} />
      {isCreating ? renderForm() : renderList()}
    </div>
  );
}
