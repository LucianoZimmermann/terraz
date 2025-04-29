import React from "react";

type ButtonType = "button" | "submit" | "reset";

type ButtonVariant = "primary" | "secondary";

interface ButtonProps {
  content: string;
  onClick?: () => void;
  type?: ButtonType;
  disabled?: boolean;
  variant?: ButtonVariant;
}

export default function Button({
  content,
  onClick,
  type = "button",
  disabled = false,
  variant = "primary",
}: ButtonProps) {
  const baseClasses = "entityButton px-4 py-2 rounded shadow";
  const variantClasses =
    variant === "primary"
      ? "bg-blue-600 hover:bg-blue-700 text-white"
      : "bg-gray-200 hover:bg-gray-300 text-gray-800";

  return (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled}
      className={`${baseClasses} ${variantClasses} ${disabled ? "opacity-50 cursor-not-allowed" : ""}`}
    >
      {content}
    </button>
  );
}
