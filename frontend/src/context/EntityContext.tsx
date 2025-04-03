import React, { createContext, useContext, useState } from "react";
import { EntityType } from "../constant/entity";

interface EntityContextType {
  entity: EntityType;
  setEntity: (entity: EntityType) => void;
}

const EntityContext = createContext<EntityContextType | undefined>(undefined);

export const EntityProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [entity, setEntity] = useState<EntityType>(EntityType.QUOTE); // default

  return (
    <EntityContext.Provider value={{ entity, setEntity }}>
      {children}
    </EntityContext.Provider>
  );
};

export const useEntity = () => {
  const context = useContext(EntityContext);
  if (!context) {
    throw new Error("useEntity must be used within an EntityProvider");
  }
  return context;
};
